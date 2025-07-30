package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftfingers.codingchallenge.docstest.AES;

/**
 * Created by Obiora on 14-Jul-2025 at 12:47
 */
public class Utils {
    public static void main(String[] args) {
        String aesKey = "3585ce7162889b6cf3df89d8593ce81f3747f01877c6fc65629757e35a829413";
        TransactionNotificationResponseDto notificationResponseDto =
                TransactionNotificationResponseDto.builder()
                        .sessionID("241103033404675000698906894002")
                        .nameEnquiryRef("241103033404675000698906894002")
                        .destinationInstitutionCode("000004")
                        .channelCode("1")
                        .beneficiaryAccountName("Autos - John Doe")
                        .beneficiaryAccountNumber("6200000044")
                        .beneficiaryBankVerificationNumber("11111111111")
                        .originatorAccountName("Adewale Hassan")
                        .originatorAccountNumber("3333002345")
                        .originatorBankVerificationNumber("10330004415")
                        .originatorKYCLevel("3")
                        .transactionLocation("6.4300747,3.4110715")
                        .narration("Test transfer")
                        .paymentReference("7962197030298791")
                        .status("Successful")
                        .amount("000.00")
                        .collectionAccountNumber("00018263487")
                        .createdOn("2024-11-03 03:34:18")
                        .updatedOn("2024-11-03 03:34:18")
                        .build();

        String jsonPayload = serializeToJson(notificationResponseDto);
        String encryptedData = encryptPayload(jsonPayload, aesKey);
        System.out.println("Encrypted Payload Data: " + encryptedData);

        String decryptedData = decryptPayload(encryptedData, aesKey);
        System.out.println("Decrypted Payload Data: " + decryptedData);
    }

    private static String serializeToJson(Object data) {
        try {
            return new ObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing payload", e);
        }
    }

    private static String encryptPayload(String jsonPayload, String aesKeyHex) {
        return AES.encrypt(jsonPayload, aesKeyHex);
    }

    private static String decryptPayload (String jsonPayload, String aesKeyHex) {
        return AES.decrypt(jsonPayload, aesKeyHex);
    }
}
