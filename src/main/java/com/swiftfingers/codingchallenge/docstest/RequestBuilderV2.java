package com.swiftfingers.codingchallenge.docstest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Obiora on 16-Jun-2025 at 15:17
 */
public class RequestBuilderV2 {
    private static final String MERCHANT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjgwexv13ilAmjHvLDGPZBa99m3sZlYoi+e7Nmmb75xkH/NLYrD8/VGFpBdS7dj4O1q3HaL2RnKhtT/3Mm/pSGxNBANkWQsqDKX3npB/yX2/pvBJxDtM+diU1hnVtCvyQ71de1Ze8dQ4JuqaJtUoSAuvgm7Up6aCrcULnY/xylT+tLlVv712ITf3fcu9DF71ucsB4HrO9GgpSLMRCHk9zi800GHxaT1bSbj9mp/YhOOCwahag3Mk70c3pt0sVfFOi1crgMuZZXdpcRZFjvLaVjHSxrNgt5xXxf51/iiogKl6/Ay1nSuZnHh22E0qDrSXMNopGrPnbovsUi/pH4bz3CwIDAQAB";
    private static final String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCODB7G/XeKUCaMe8sMY9kFr32bexmViiL57s2aZvvnGQf80tisPz9UYWkF1Lt2Pg7WrcdovZGcqG1P/cyb+lIbE0EA2RZCyoMpfeekH/Jfb+m8EnEO0z52JTWGdW0K/JDvV17Vl7x1Dgm6pom1ShIC6+CbtSnpoKtxQudj/HKVP60uVW/vXYhN/d9y70MXvW5ywHges70aClIsxEIeT3OLzTQYfFpPVtJuP2an9iE44LBqFqDcyTvRzem3SxV8U6LVyuAy5lld2lxFkWO8tpWMdLGs2C3nFfF/nX+KKiAqXr8DLWdK5mceHbYTSoOtJcw2ikas+dui+xSL+kfhvPcLAgMBAAECggEANDI79P8auBZvs9uhZzIhgzdTPpjjc9HIvHKF1qDVG7xU9RA9Uj4DdWw9/UiIghCXv0coKVLPePNjB6vJTd+T4CMZFcivp2dm/w4m2mZq16r2Bof4R2xUXXGa04e+w/S/BVPjozUYqSK5bH7Zx2YqbCxk4pKVjiXeGcZt5I3VRQYN0PkHuDTdOXaseruZFYULJkvL/WTeWicgk4y1oAV2e6CzxGRN3RYtQ9+MTS4v1d3JQbuG0Co7WIsp6lOmNWd9ofVpOe0jUBtutU917P2zvJ0A6CMkwdNYgpu0FAN85j3r7YZwafjj4QmMObdMDlugvp8dENNuFv8gj9bXYJSJAQKBgQDX52+/0pVEw+9BHYrSL3WO4fVA45ryKXhlpE3BJrcweL5gLS4XoRTKiN8pPfSQcG/XBWKgVuT58z++BhtmaUjs7SvxgAmVwoMbq/G76qRjk3SgiJxuHVMF9EjanGlqHqZ2ClEAx6G7KA8Efq11udMIQviytnKEyjDOsDIrxlarQQKBgQCobV69LLNJGymT4S46bPI09J+8DhQujnUbo7oJWMQNvbR0fVLJSeLCCGeodDTAIUWisdmQWtlQMNmet8geC0N8o2KKGaH3ENHKUaeHtk7+OITzjkf8XuzimBBDN7O7fcvHgBinZWITq+1ts/Ty2MWlgOPWw8YtFhfQuRyhHr0LSwKBgAmhJefNUaEGiCZEVgu32CtuQo0JWcDRPrBx2pHx0xvTVtJdUX/2TEIYRrPFMqI6kZJqiBKUrZPEWKR2C0TPD1SZ42rbBhJq4YQbcCWsenaCfiIm1atHnYtXcORnM4BrK4t6PFCnlrwNNlZB/CSTDCmDKyp1XrgzeGfPjf1L1FPBAoGAEhJqL/Iz6ow3kd5/j1VRR81Xtl3sXHJ8rlKLC6Wyo6bPThCEaYS8CAXO2YxefJ8VUbezmIGm/6u+LgGaP18GBsGhOSA2+MOdPxAz4aP2JeD6oweJQdMNp+KZHB1F88AztVxRSOTf+SxhdXvxtHz9+Bw0Vj77bKsKMyMpp0AdGLECgYAF5uPY30zSCJlDnTAEsPNZ8q2IGwRQomfW6RcEUyUqZ326tojGEnFFsTfvDvliCnL55yyNoXrbqHalMlnZJuOqETINq16Pd7JgYY+NfiMQKkV6EAa2D1R3rmqtnJJ3h/dNjHL9ON7RpjWza/eGEYcKdrclzCMyP9aSAtsXFN3N0g==";
    private static final String VAS_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsKwdh/9qcAsspqvBTNwRCOH0/uMvCEP/JyOe+oZA+GrCbFs9Ov43bpgUr3Y+Vm5AM1f7LbSGakh6pAUOXFYJpJ1SgNamRAjoBb21pdnmZelFATDuYrPqJfRZx4gfAWstmN1mhWOmw1sc5s9RJXUk6ShkunVRv3jbJChDiikPs7gkeygScJeY6WJljAXCo9qHV7l+mBy+6XKv6lxT7+VFeh3YYvV/x4+Gnos8HWgLvST1J8erQWudHrSs7ZNztKhpB0mgEZ+UCfhretftEE7B15EG9YmKfsstnAr9BAZGLcpxTBFPJ+HahBqjpooMWxFXhAkdxx1MSxQGY5A57uS19QIDAQAB";

    public static void main(String[] args)  throws Exception{
        openVirtualAccount();
   //     queryVirtualAccountInflow();
     //   transferFunds();
//        requery();
    }

    private static String generateRandomHexKey(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length * 2) {
            sb.append(Integer.toHexString(random.nextInt(256) | 0x100).substring(1));
        }
        return sb.toString().substring(0, length * 2);
    }

    private static String generateAesKeyHex() {
        SecureRandom random = new SecureRandom();
        byte[] aesKeyBytes = new byte[32];
        random.nextBytes(aesKeyBytes);

        StringBuilder hexKey = new StringBuilder();
        for (byte b : aesKeyBytes) {
            hexKey.append(String.format("%02x", b));
        }
        return hexKey.toString();
    }

    private static void generateRequest (String payloadHash, String jsonPayload) throws JsonProcessingException {
        //        // Sign the hashed payload with merchant private key
        ObjectMapper objectMapper = new ObjectMapper();
        String signature = new RSA().signWithPrivateKey(payloadHash, MERCHANT_PRIVATE_KEY);
        System.out.println("Signature: " + signature);
//
//        //  Generate AES key (as hex string, 32 bytes = 64 hex characters)
        String aesKey = generateAesKeyHex();
        System.out.println("AES Key (hex): " + aesKey);
//
//        // Encrypt JSON payload using AES key
        String encryptedPayload = AES.encrypt(jsonPayload, aesKey);
        System.out.println("Encrypted Payload: " + encryptedPayload);
//
//        // Encrypt AES key with VAS public key for SecureToken
        String secureToken = new RSA().encryptWithPublicKey(aesKey, VAS_PUBLIC_KEY);
        System.out.println("SecureToken: " + secureToken);
//
//        // Compute Authorization header value
        String authorization = "Bearer " + Sha256.hash(MERCHANT_PUBLIC_KEY);
        System.out.println("Authorization: " + authorization);
//
//        // Final Headers:
        System.out.println("\n=== Headers ===");
        System.out.println("Authorization: " + authorization);
        System.out.println("Signature: " + signature);
        System.out.println("SecureToken: " + secureToken);
        System.out.println("Content-Type: application/json");
//
////        // Final Request Body:
//        String finalRequestBody = objectMapper.writeValueAsString(Map.of("data", encryptedPayload));
//        System.out.println("\n=== Request Body ===");
//        System.out.println(finalRequestBody);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("data", encryptedPayload);

        String finalRequestBody = objectMapper.writeValueAsString(requestBody);
        System.out.println("Final request Body >>>  " + finalRequestBody);

        String responsePayload =
                "+2liDx6/2XDXlSc2nGfGTzStBk7sRRcYYrWf3PhMF5QMQXT2jVa0FnY99KAY0gU54rMqKYW/zBW/t87ScsS0nVO4xw2BgYB90RQ9EolU/2bI/Ek6IAH/6zoAxzLWSMx0l/GcvI7xz0Dwir5vN294yEmus+Fr6yOgUMwrn3ScG2zMmuOqcYgh3ozD7QDi5t8Ev+Zo+9Q9m0TEffgJQPBrbv1hSETmYp6yina6AOtqOFa9oCWIYW0EsTTErkx674NKUWq1LVHKVS3mP9UXXHpkysxZLOcCPZc87Du7aK8EOYyDbZlI40b8oj4CeE9Aq8Mnx0hc8SVhLUWJfxyhG8bn7PIAgxXXjSmHLmEmfGq7YOn+SQl8lzp5yXoKDz4DCla/Hfr4rVV4UaNJ8iVG8Xw59qR2Piy/vEHUGdzYJunCfSM=";
                                            String dc = AES.decrypt(responsePayload, "f6f4a6ec87406501542b5fffc0a1e94b8501813bcfbcdcf3199c453e386f29db");
        System.out.println("DC +++++++++++++++ " + dc);
    }

    private static void openVirtualAccount () throws JsonProcessingException {
        // Initialize helpers
        ObjectMapper objectMapper = new ObjectMapper();
        AES aes = new AES();
        RSA rsa = new RSA();


        // Request payload DTO
        OpenVirtualAccountRequest request = new OpenVirtualAccountRequest();
        request.setBvn("2222222222");
        request.setFirstName("Timothy");
        request.setLastName("Ogundele");
        request.setMiddleName("Bamidele");
        request.setAccountName("Timothy Bamidele Ogundele");
        request.setEmail("martins123@gmail.com");
        request.setPhone("2348022332211");
        request.setProductType("ABC");
        //   request.setStatus("ACTIVE");
        request.setCustomerReference("46d8te673gwj374");
        request.setExpireAt(null);
        request.setSingleDepositLimit("1000");

        OpenVirtualAccountRequest.Merchant merchant = new OpenVirtualAccountRequest.Merchant();
        merchant.setCode("E5EC4");
        request.setMerchant(merchant);

        Map<String, String> meta = Map.of("product_code", "SEERBIT");
        request.setMeta(null);

        // Convert to JSON string
        String jsonPayload = objectMapper.writeValueAsString(request);
        System.out.println("JSON Payload: " + jsonPayload);

//        // Generate SHA256 hash of payload for signature
        String payloadHash = Sha256.hash(jsonPayload);
        System.out.println("Payload Hash: " + payloadHash);

        generateRequest(payloadHash, jsonPayload);
    }

    private static void queryVirtualAccountInflow () throws JsonProcessingException {
        // Request payload DTO
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> request = new HashMap<>();
        request.put("vNUBAN", "6200023458");
        request.put("startDate","2025-01-01");
        request.put("endDate", "2025-06-14");

        String jsonPayload = objectMapper.writeValueAsString(request);
        System.out.println("JSON Payload for Virtual Account Inflow:::::::: " + jsonPayload);

//        // Generate SHA256 hash of payload for signature
        String payloadHash = Sha256.hash(jsonPayload);
        System.out.println("Payload Hash for Virtual Account Inflow::::::::: " + payloadHash);
        generateRequest(payloadHash, jsonPayload);
    }

    private static void transferFunds () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TransferFunds transferFunds = new TransferFunds();
        transferFunds.setNarration("Transfer to virtual");
        transferFunds.setDestinationAccount("2096763285");
        transferFunds.setSourceAccount("1019464009");
        transferFunds.setTransferAmount("100");
        transferFunds.setTransactionRef(UUID.randomUUID().toString().substring(0,12));
        transferFunds.setDestinationAccountName("MAURICE UBOK-UDOM");
        transferFunds.setDestinationBankCode("060");

        String jsonPayload = objectMapper.writeValueAsString(transferFunds);

        System.out.println("JSON Payload for Transfer Funds :::::::: " + jsonPayload);

//        // Generate SHA256 hash of payload for signature
        String payloadHash = Sha256.hash(jsonPayload);
        System.out.println("Payload Hash for Transfer Funds ::::::::: " + payloadHash);
        generateRequest(payloadHash, jsonPayload);
    }

    private static void requery () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Requery requery = new Requery();
        requery.setTransactionRef("4113d42d-bb8");
        requery.setAccountNumber("2096763285");
        requery.setStan("525871609515");

        String jsonPayload = objectMapper.writeValueAsString(requery);

        System.out.println("JSON Payload for Transfer Requery:::::::: " + jsonPayload);

//        // Generate SHA256 hash of payload for signature
        String payloadHash = Sha256.hash(jsonPayload);
        System.out.println("Payload Hash for Requery ::::::::: " + payloadHash);
        generateRequest(payloadHash, jsonPayload);
    }


}
