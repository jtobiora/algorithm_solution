package com.swiftfingers.codingchallenge.virtualaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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



}