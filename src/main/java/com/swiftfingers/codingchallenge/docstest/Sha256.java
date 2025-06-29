package com.swiftfingers.codingchallenge.docstest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Obiora on 13-Jun-2025 at 10:26
 */
public class Sha256 {

    private Sha256() {
        throw new IllegalStateException("Utility class");
    }

    @SneakyThrows
    public static String hash(String input){
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {

        //Public Key Hash
        String publicKey = "\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjgwexv13ilAmjHvLDGPZBa99m3sZlYoi+e7Nmmb75xkH/NLYrD8/VGFpBdS7dj4O1q3HaL2RnKhtT/3Mm/pSGxNBANkWQsqDKX3npB/yX2/pvBJxDtM+diU1hnVtCvyQ71de1Ze8dQ4JuqaJtUoSAuvgm7Up6aCrcULnY/xylT+tLlVv712ITf3fcu9DF71ucsB4HrO9GgpSLMRCHk9zi800GHxaT1bSbj9mp/YhOOCwahag3Mk70c3pt0sVfFOi1crgMuZZXdpcRZFjvLaVjHSxrNgt5xXxf51/iiogKl6/Ay1nSuZnHh22E0qDrSXMNopGrPnbovsUi/pH4bz3CwIDAQAB";
        String publicKeyHash = hash(publicKey);
        System.out.println("Public Key Hash: " + publicKeyHash);

        // 1. Build the nested payload (structured way)
        Map<String, Object> payload = new HashMap<>();
        payload.put("bvn", "2222222222");
        payload.put("firstName", "Martins");
        payload.put("lastName", "Kayode");
        payload.put("middleName", "Gbenga");
        payload.put("accountName", "Martins Gbenga Kayode");
        payload.put("email", "martins123@gmail.com");
        payload.put("phone", "2348022332211");
        payload.put("productType", "ABC");
        payload.put("customerReference", "b9956a00-24ee-49cf-96c0-1c504678th44733");
        payload.put("expireAt", "2024-04-30T15:40:56.409+0100");
        payload.put("singleDepositLimit", "1000");

        // Nested objects
        Map<String, String> merchant = new HashMap<>();
        merchant.put("code", "MER89");
        payload.put("merchant", merchant);

        Map<String, String> meta = new HashMap<>();
        meta.put("product_code", "PR089");
        payload.put("meta", meta);

        // 2. Serialize to JSON (consistent formatting)
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true); // Sort keys
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false); // No extra whitespace

        try {
            String jsonPayload = mapper.writeValueAsString(payload);
            System.out.println("JSON Payload:\n" + jsonPayload);

            // 3. Hash using the existing Sha256 class
            String hash = Sha256.hash(jsonPayload);
            System.out.println("\nSHA-256 Hashed Payload:\n" + hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
