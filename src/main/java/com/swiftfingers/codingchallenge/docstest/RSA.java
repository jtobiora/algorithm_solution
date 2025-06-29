package com.swiftfingers.codingchallenge.docstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by Obiora on 13-Jun-2025 at 10:26
 */
@Service
public class RSA {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SECURE_PADDING = "RSA/ECB/PKCS1Padding";

    public String encryptWithPublicKey(String plainText, String publicKeyContent) {

        byte[] bytes;
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyContent);
            KeyFactory publicKeyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = publicKeyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            Cipher cipher = Cipher.getInstance(SECURE_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeySpecException e) {
            logger.error("error ==> {}", e.getMessage(), e);
            return plainText;
        }

        return new String(Base64.getEncoder().encode(bytes));
    }

    public String decryptWithPrivateKey(String encodedText, String privateKeyContent) {

        byte[] bytes;
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyContent);
            KeyFactory privateKeyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = privateKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            Cipher cipher = Cipher.getInstance(SECURE_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            bytes = cipher.doFinal(Base64.getDecoder().decode(encodedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeySpecException e) {
            logger.error("error ==> {}", e.getMessage(), e);
            return encodedText;
        }

        return new String(bytes, StandardCharsets.UTF_8);

    }

    public String signWithPrivateKey(String plainText, String privateKeyContent)
    {
        try
        {
            // Decode the private key from Base64
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyContent);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // Initialize the Signature with the private key and SHA-256 with RSA
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);

            // Update the signature with the message bytes
            signature.update(plainText.getBytes(StandardCharsets.UTF_8));

            // Sign the message and encode it in Base64
            byte[] signedMessage = signature.sign();
            return Base64.getEncoder().encodeToString(signedMessage);

        } catch (Exception e)
        {
            logger.error("Error during signing: {}", e.getMessage());
            return null;
        }
    }


    public boolean verifySignatureWithPublicKey(String plainText, String encodedText, String publicKeyContent)
    {
        try
        {
            // Decode the public key from Base64
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Decode the signature from Base64
            byte[] signatureBytes = Base64.getDecoder().decode(encodedText);

            // Initialize the Signature with the public key and SHA-256 with RSA
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(plainText.getBytes(StandardCharsets.UTF_8));

            // Verify the signature
            return signature.verify(signatureBytes);
        }
        catch (Exception e)
        {
            logger.error("Signature Verification Failed: {}", e.getMessage());
            return false;
        }
    }

    private String generateRandomHexString() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16]; // 128-bit key
        secureRandom.nextBytes(bytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        System.out.println("AES Key (hex): " + hexString.toString());
        return hexString.toString();
    }

    public String generateSignature () {
        String requestPayloadHash = "04b4c707636a5ca937c488d8b6a36fb21a22f4b4ec2f901fa5cb67aa320389a3";
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCODB7G/XeKUCaMe8sMY9kFr32bexmViiL57s2aZvvnGQf80tisPz9UYWkF1Lt2Pg7WrcdovZGcqG1P/cyb+lIbE0EA2RZCyoMpfeekH/Jfb+m8EnEO0z52JTWGdW0K/JDvV17Vl7x1Dgm6pom1ShIC6+CbtSnpoKtxQudj/HKVP60uVW/vXYhN/d9y70MXvW5ywHges70aClIsxEIeT3OLzTQYfFpPVtJuP2an9iE44LBqFqDcyTvRzem3SxV8U6LVyuAy5lld2lxFkWO8tpWMdLGs2C3nFfF/nX+KKiAqXr8DLWdK5mceHbYTSoOtJcw2ikas+dui+xSL+kfhvPcLAgMBAAECggEANDI79P8auBZvs9uhZzIhgzdTPpjjc9HIvHKF1qDVG7xU9RA9Uj4DdWw9/UiIghCXv0coKVLPePNjB6vJTd+T4CMZFcivp2dm/w4m2mZq16r2Bof4R2xUXXGa04e+w/S/BVPjozUYqSK5bH7Zx2YqbCxk4pKVjiXeGcZt5I3VRQYN0PkHuDTdOXaseruZFYULJkvL/WTeWicgk4y1oAV2e6CzxGRN3RYtQ9+MTS4v1d3JQbuG0Co7WIsp6lOmNWd9ofVpOe0jUBtutU917P2zvJ0A6CMkwdNYgpu0FAN85j3r7YZwafjj4QmMObdMDlugvp8dENNuFv8gj9bXYJSJAQKBgQDX52+/0pVEw+9BHYrSL3WO4fVA45ryKXhlpE3BJrcweL5gLS4XoRTKiN8pPfSQcG/XBWKgVuT58z++BhtmaUjs7SvxgAmVwoMbq/G76qRjk3SgiJxuHVMF9EjanGlqHqZ2ClEAx6G7KA8Efq11udMIQviytnKEyjDOsDIrxlarQQKBgQCobV69LLNJGymT4S46bPI09J+8DhQujnUbo7oJWMQNvbR0fVLJSeLCCGeodDTAIUWisdmQWtlQMNmet8geC0N8o2KKGaH3ENHKUaeHtk7+OITzjkf8XuzimBBDN7O7fcvHgBinZWITq+1ts/Ty2MWlgOPWw8YtFhfQuRyhHr0LSwKBgAmhJefNUaEGiCZEVgu32CtuQo0JWcDRPrBx2pHx0xvTVtJdUX/2TEIYRrPFMqI6kZJqiBKUrZPEWKR2C0TPD1SZ42rbBhJq4YQbcCWsenaCfiIm1atHnYtXcORnM4BrK4t6PFCnlrwNNlZB/CSTDCmDKyp1XrgzeGfPjf1L1FPBAoGAEhJqL/Iz6ow3kd5/j1VRR81Xtl3sXHJ8rlKLC6Wyo6bPThCEaYS8CAXO2YxefJ8VUbezmIGm/6u+LgGaP18GBsGhOSA2+MOdPxAz4aP2JeD6oweJQdMNp+KZHB1F88AztVxRSOTf+SxhdXvxtHz9+Bw0Vj77bKsKMyMpp0AdGLECgYAF5uPY30zSCJlDnTAEsPNZ8q2IGwRQomfW6RcEUyUqZ326tojGEnFFsTfvDvliCnL55yyNoXrbqHalMlnZJuOqETINq16Pd7JgYY+NfiMQKkV6EAa2D1R3rmqtnJJ3h/dNjHL9ON7RpjWza/eGEYcKdrclzCMyP9aSAtsXFN3N0g==";
        return signWithPrivateKey(requestPayloadHash, privateKey);
    }

    public String generateSecureToken () {
        String vasPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsKwdh/9qcAsspqvBTNwRCOH0/uMvCEP/JyOe+oZA+GrCbFs9Ov43bpgUr3Y+Vm5AM1f7LbSGakh6pAUOXFYJpJ1SgNamRAjoBb21pdnmZelFATDuYrPqJfRZx4gfAWstmN1mhWOmw1sc5s9RJXUk6ShkunVRv3jbJChDiikPs7gkeygScJeY6WJljAXCo9qHV7l+mBy+6XKv6lxT7+VFeh3YYvV/x4+Gnos8HWgLvST1J8erQWudHrSs7ZNztKhpB0mgEZ+UCfhretftEE7B15EG9YmKfsstnAr9BAZGLcpxTBFPJ+HahBqjpooMWxFXhAkdxx1MSxQGY5A57uS19QIDAQAB";
        String aesKeyHex = generateRandomHexString();
        RSA rsa = new RSA();
        String secureToken = rsa.encryptWithPublicKey(aesKeyHex, vasPublicKey);
        System.out.println("SecureToken: " + secureToken);
        return secureToken;
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println("RSA Signed Signature : " + rsa.generateSignature());
    }

}

