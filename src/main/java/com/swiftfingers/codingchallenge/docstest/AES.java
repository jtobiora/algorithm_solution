package com.swiftfingers.codingchallenge.docstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Obiora on 13-Jun-2025 at 10:26
 */
@Service
public class AES {

    private static final Logger logger = LoggerFactory.getLogger(AES.class);
    public static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String KEY_ALGORITHM = "AES";

    public static String encrypt(String plainString, String key) {
        try
        {
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);

            byte[] keyBytes = hexStringToByteArray(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(plainString.getBytes(StandardCharsets.UTF_8));

            byte[] ivAndEncrypted = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, ivAndEncrypted, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(ivAndEncrypted);
        }
        catch (Exception e)
        {
            logger.error("error ==> {}", e.getMessage(), e);
            return plainString;
        }
    }

    public static String decrypt(String encryptedString, String key) {
        try
        {
            byte[] combined = Base64.getDecoder().decode(encryptedString);

            byte[] iv = new byte[16];
            System.arraycopy(combined, 0, iv, 0, 16);

            byte[] ciphertext = new byte[combined.length - 16];
            System.arraycopy(combined, 16, ciphertext, 0, ciphertext.length);

            byte[] keyBytes = hexStringToByteArray(key);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), new IvParameterSpec(iv));

            return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);

        }
        catch (Exception e)
        {
            logger.error("error ==> {}", e.getMessage(), e);
            return encryptedString;
        }
    }

    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public void generatePayloadData () {
        String aesKeyHex = "";
        String jsonPayload = "";
        String encryptedData = AES.encrypt(jsonPayload, aesKeyHex);
        System.out.println("Encrypted Payload Data: " + encryptedData);
    }

    public static void main(String[] args) {
        AES aes = new AES();
        aes.generatePayloadData();
    }

}
