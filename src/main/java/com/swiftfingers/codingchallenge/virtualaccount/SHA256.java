package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256 {

    private SHA256() {
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
}