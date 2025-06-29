package com.swiftfingers.codingchallenge.docstest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class UBAVirtualAccountAPITest {
    private static final String MERCHANT_PUBLIC_KEY = "MERCHANT_PUBLIC_KEY";
    private static final String MERCHANT_PRIVATE_KEY = "MERCHANT_PRIVATE_KEY";
    private static final String VAS_PUBLIC_KEY = "VAS_PUBLIC_KEY";
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
