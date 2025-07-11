package com.swiftfingers.codingchallenge.docstest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class UBAVirtualAccountAPITest {
    private static final String MERCHANT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjgwexv13ilAmjHvLDGPZBa99m3sZlYoi+e7Nmmb75xkH/NLYrD8/VGFpBdS7dj4O1q3HaL2RnKhtT/3Mm/pSGxNBANkWQsqDKX3npB/yX2/pvBJxDtM+diU1hnVtCvyQ71de1Ze8dQ4JuqaJtUoSAuvgm7Up6aCrcULnY/xylT+tLlVv712ITf3fcu9DF71ucsB4HrO9GgpSLMRCHk9zi800GHxaT1bSbj9mp/YhOOCwahag3Mk70c3pt0sVfFOi1crgMuZZXdpcRZFjvLaVjHSxrNgt5xXxf51/iiogKl6/Ay1nSuZnHh22E0qDrSXMNopGrPnbovsUi/pH4bz3CwIDAQAB";
    private static final String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCODB7G/XeKUCaMe8sMY9kFr32bexmViiL57s2aZvvnGQf80tisPz9UYWkF1Lt2Pg7WrcdovZGcqG1P/cyb+lIbE0EA2RZCyoMpfeekH/Jfb+m8EnEO0z52JTWGdW0K/JDvV17Vl7x1Dgm6pom1ShIC6+CbtSnpoKtxQudj/HKVP60uVW/vXYhN/d9y70MXvW5ywHges70aClIsxEIeT3OLzTQYfFpPVtJuP2an9iE44LBqFqDcyTvRzem3SxV8U6LVyuAy5lld2lxFkWO8tpWMdLGs2C3nFfF/nX+KKiAqXr8DLWdK5mceHbYTSoOtJcw2ikas+dui+xSL+kfhvPcLAgMBAAECggEANDI79P8auBZvs9uhZzIhgzdTPpjjc9HIvHKF1qDVG7xU9RA9Uj4DdWw9/UiIghCXv0coKVLPePNjB6vJTd+T4CMZFcivp2dm/w4m2mZq16r2Bof4R2xUXXGa04e+w/S/BVPjozUYqSK5bH7Zx2YqbCxk4pKVjiXeGcZt5I3VRQYN0PkHuDTdOXaseruZFYULJkvL/WTeWicgk4y1oAV2e6CzxGRN3RYtQ9+MTS4v1d3JQbuG0Co7WIsp6lOmNWd9ofVpOe0jUBtutU917P2zvJ0A6CMkwdNYgpu0FAN85j3r7YZwafjj4QmMObdMDlugvp8dENNuFv8gj9bXYJSJAQKBgQDX52+/0pVEw+9BHYrSL3WO4fVA45ryKXhlpE3BJrcweL5gLS4XoRTKiN8pPfSQcG/XBWKgVuT58z++BhtmaUjs7SvxgAmVwoMbq/G76qRjk3SgiJxuHVMF9EjanGlqHqZ2ClEAx6G7KA8Efq11udMIQviytnKEyjDOsDIrxlarQQKBgQCobV69LLNJGymT4S46bPI09J+8DhQujnUbo7oJWMQNvbR0fVLJSeLCCGeodDTAIUWisdmQWtlQMNmet8geC0N8o2KKGaH3ENHKUaeHtk7+OITzjkf8XuzimBBDN7O7fcvHgBinZWITq+1ts/Ty2MWlgOPWw8YtFhfQuRyhHr0LSwKBgAmhJefNUaEGiCZEVgu32CtuQo0JWcDRPrBx2pHx0xvTVtJdUX/2TEIYRrPFMqI6kZJqiBKUrZPEWKR2C0TPD1SZ42rbBhJq4YQbcCWsenaCfiIm1atHnYtXcORnM4BrK4t6PFCnlrwNNlZB/CSTDCmDKyp1XrgzeGfPjf1L1FPBAoGAEhJqL/Iz6ow3kd5/j1VRR81Xtl3sXHJ8rlKLC6Wyo6bPThCEaYS8CAXO2YxefJ8VUbezmIGm/6u+LgGaP18GBsGhOSA2+MOdPxAz4aP2JeD6oweJQdMNp+KZHB1F88AztVxRSOTf+SxhdXvxtHz9+Bw0Vj77bKsKMyMpp0AdGLECgYAF5uPY30zSCJlDnTAEsPNZ8q2IGwRQomfW6RcEUyUqZ326tojGEnFFsTfvDvliCnL55yyNoXrbqHalMlnZJuOqETINq16Pd7JgYY+NfiMQKkV6EAa2D1R3rmqtnJJ3h/dNjHL9ON7RpjWza/eGEYcKdrclzCMyP9aSAtsXFN3N0g==";
    private static final String VAS_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsKwdh/9qcAsspqvBTNwRCOH0/uMvCEP/JyOe+oZA+GrCbFs9Ov43bpgUr3Y+Vm5AM1f7LbSGakh6pAUOXFYJpJ1SgNamRAjoBb21pdnmZelFATDuYrPqJfRZx4gfAWstmN1mhWOmw1sc5s9RJXUk6ShkunVRv3jbJChDiikPs7gkeygScJeY6WJljAXCo9qHV7l+mBy+6XKv6lxT7+VFeh3YYvV/x4+Gnos8HWgLvST1J8erQWudHrSs7ZNztKhpB0mgEZ+UCfhretftEE7B15EG9YmKfsstnAr9BAZGLcpxTBFPJ+HahBqjpooMWxFXhAkdxx1MSxQGY5A57uS19QIDAQAB";
    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args)  throws Exception{
        UBAVirtualAccountAPITest accountAPITest = new UBAVirtualAccountAPITest();
        accountAPITest.openVirtualAccount();
        accountAPITest.updateVirtualAccount();
        accountAPITest.transferFunds();
    }

    private void openVirtualAccount () throws JsonProcessingException {
        OpenVirtualAccountRequest request = new OpenVirtualAccountRequest();
        request.setBvn("2222222222");
        request.setFirstName("Johnson");
        request.setLastName("Adeleke");
        request.setMiddleName("Bolade");
        request.setAccountName("Johnson Bolade Adeleke");
        request.setEmail("johnson.bolade123@gmail.com");
        request.setPhone("2348064332333");
        request.setProductType("ABC");
        request.setCustomerReference(UUID.randomUUID().toString().substring(0,12));
        request.setExpireAt(null);
        request.setSingleDepositLimit("1000");

        OpenVirtualAccountRequest.Merchant merchant = new OpenVirtualAccountRequest.Merchant();
        merchant.setCode("E5EC4");
        request.setMerchant(merchant);

       // Map<String, String> meta = Map.of("product_code", "");
        request.setMeta(null);

        printResponse(request);
    }

    private void updateVirtualAccount () throws JsonProcessingException {
        UpdateVirtualAccountRequest updateRequest = UpdateVirtualAccountRequest.builder()
                .firstName("John")
                .middleName("Andrew")
                .lastName("Paul")
                .accountName("John Andrew Paul")
                .productType("ABC")
                .meta(null)
                .build();

        printResponse(updateRequest);
    }

    private void transferFunds () throws JsonProcessingException {
        TransferFunds transferFunds = new TransferFunds();
        transferFunds.setNarration("Transfer test");
        transferFunds.setDestinationAccount("2096763285");
        transferFunds.setSourceAccount("1019464009");
        transferFunds.setTransferAmount("100");
        transferFunds.setTransactionRef(UUID.randomUUID().toString().substring(0,12));
        transferFunds.setDestinationAccountName("MAURICE UBOK-UDOM");
        transferFunds.setDestinationBankCode("060");

        printResponse(transferFunds);
    }

    private void printResponse (Object request) throws JsonProcessingException {
        RSA rsa = new RSA();
        String jsonPayload = serializeToJson(request);
        log.info("JSON Payload: {}", jsonPayload);

        String payloadHash = Sha256.hash(jsonPayload);
        log.info("Payload Hash: {}", payloadHash);

        String signature = rsa.signWithPrivateKey(payloadHash, MERCHANT_PRIVATE_KEY);
        log.info("Signature: {}", signature);

        String aesKeyHex = generateAesKeyHex();
        log.info("AES Key (hex): {}", aesKeyHex);

        String encryptedPayload = AES.encrypt(jsonPayload, aesKeyHex);
        log.info("Encrypted Payload: {}", encryptedPayload);

        String secureToken = rsa.encryptWithPublicKey(aesKeyHex, VAS_PUBLIC_KEY);
        log.info("SecureToken: {}", secureToken);

        String authorization = "Bearer " + Sha256.hash(MERCHANT_PUBLIC_KEY);
        log.info("Authorization: {}", authorization);

        Map<String, String> requestBody = Map.of("data", encryptedPayload);
        String finalRequestBody = objectMapper.writeValueAsString(requestBody);
        log.info("Final request Body: {}", finalRequestBody);

        String responsePayload = "WS9si+7LH0eYckn8NOV0h7Sj45avIqKGjO1Wmga+p0gNRbiUZEotY/iuOg1mkasZuSV6wrF6aw15vOC4l61RWWKZqDoIh46eGQW1KSeuWTyUrcOjbwBZkI8rKTr2rg/qQp7fCK+lYDUoFIRLMPffqbt6kQg2MARmjhzUajRZlL03f7Nh5K63Ze3MqHDfDIe8P/UkQDy4jCXoEZoKsRBtW/Ij6uinpZ9q0oMO6dmuC3sN2cvs9AsABZfV+Q/AORILFVG/6V5WpNqfgC7Mb1B4BEynNxb+3/yiNq3XS5D9L57WJoH5Zp20eLDyVqLt/1LpwtmtPdxxRI+FOmTA8X3KDZGg1UWJPowPdGJPwnbvyhZbCfANlsysFRZHXUR3ojLSuUbaf0Bve0RCUKyJSIqi7ZSyDSpP5iW6EUs210a4LcQW1aOqjpsO2WmMCPoxx0HteA038FtQaSVAXeIDONpR6Irhg3FwaSk6TxOVQykGtMLIAV4pJE7ZNCUw2ZUkVWgWx0ccbXMqYEHvGkVvjIXPKze8U03xXPHoSQAFyc/v77WeR6L17cIx2Om8GQfalcJBpCR0kvrxMCQ17m0VzOQaLbtH6hcHBprqVrB5lg4/IylndPTBUNdl7WDnU9FtGS1FeOqOfwFRvw4dNqVGzO2q0+ZD+LjeLJs6b/RjhU/fhuHZhGHmJ03GsHyCi9Hf/FrOEOuf3ZVk1nB7QVzk3/GZPH/Y/Dpkc0QJaLzBDGCcyUJoy5xXHR7NF/U4qfTEEsFNmtxa6OjLDLeXOTBQrJCSw/yAYDDK+zaCCxY+bnfLyAgQqFRT2mmyQf93Rlyf6LBi";
        String dc = AES.decrypt(responsePayload, "709f20e316ab78350c1460858f19a0254c68db9bc8d1a3704c3274a43a067e7b");
        System.out.println("DC +++++++++++++++ " + dc);
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

    private String serializeToJson (Object data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
