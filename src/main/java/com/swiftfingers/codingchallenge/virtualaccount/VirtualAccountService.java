package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftfingers.codingchallenge.docstest.AES;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Obiora on 14-Jul-2025 at 09:43
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VirtualAccountService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = PropertyConfig.getProperty("app.base-url");

    public VirtualAccountsResponseDto getAllVirtualAccounts() throws JsonProcessingException {
        String url = BASE_URL + "/virtual/accounts";
        VirtualAccountsResponseDto get = loadRequest(url, "GET", null, VirtualAccountsResponseDto.class);
        return get;
    }

    public TransactionNotificationResponseDto getTransactionNotificationWebhook (String payload, HttpServletRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String secureToken = request.getHeader("SecureToken");
        String decryptedKey = new RSA().decryptWithPrivateKey(secureToken, PropertyConfig.getProperty("merchant.private-key"));

        JsonNode rootNode = mapper.readTree(payload);
        String encryptedPayload = rootNode.get("data").asText();

        String decryptedData = AES.decrypt(encryptedPayload, decryptedKey);

        TransactionNotificationResponseDto notificationResponseDto = mapper.readValue(decryptedData, TransactionNotificationResponseDto.class);

        return notificationResponseDto;

    }

    public TransactionInflowQueryDto queryVirtualAccountInflow (TransactionQueryDto request) {
        return sendSecureRequest("/virtual/notifications/query", "POST", request, TransactionInflowQueryDto.class);
    }

    public VirtualAccountResponseDto getVirtualAccount(String accountNumber) throws JsonProcessingException {
        String url = BASE_URL + "/virtual/accounts/" + accountNumber;
        return loadRequest(url, "GET", null, VirtualAccountResponseDto.class);
    }

    public VirtualAccountCreateResponseDto openVirtualAccount(OpenVirtualAccountDto request) {
        return sendSecureRequest("/virtual/accounts", "POST", request, VirtualAccountCreateResponseDto.class);
    }

    public VirtualAccountUpdateResponseDto updateVirtualAccount(UpdateVirtualAccountDto request, String accountNumber) {
        return sendSecureRequest("/virtual/accounts/" + accountNumber, "PUT", request, VirtualAccountUpdateResponseDto.class);
    }

    public FundsTransferResponseDto transferFunds(FundsTransferDto request) {
        return sendSecureRequest("/transactions/transfer-funds", "POST", request, FundsTransferResponseDto.class);
    }

    public String requeryTransferStatus(RequeryDto request) {
        return sendSecureRequest("/transactions/transfer-funds/query", "POST", request, String.class);
    }


    private <T> T loadRequest(String url, String method, Object payload, Class<T> responseType) {
        String aesKey = generateAesKeyHex();
        System.out.println("************** AES private Key ************** " + aesKey);
        //#############################################
        String secureToken = new RSA().encryptWithPublicKey(aesKey, PropertyConfig.getProperty("merchant.public-key"));
        //#############################################
        System.out.println("*************** SecureToken *************** " + secureToken);
        String decrypted = new RSA().decryptWithPrivateKey(secureToken, PropertyConfig.getProperty("merchant.private-key"));
        System.out.println("************** decrypted AES Key ******************* " + decrypted);

        Map<String, String> headers = buildHeaders(aesKey, null, null, false);

        try {
            String response = HttpClientUtil.sendRequest(url, method, payload, headers, aesKey);
            log.info("API Response >>>>>>>>> " + response);
            return objectMapper.readValue(response, responseType);
        } catch (Exception e) {
            throw new RuntimeException("Error calling " + url, e);
        }
    }

    private <T> T sendSecureRequest(String path, String method, Object payload, Class<T> responseType) {
        String url = BASE_URL + path;
        String jsonPayload = serializeToJson(payload);
        String hashedPayload = hashPayload(jsonPayload);
        String aesKey = generateAesKeyHex();
        String signature = generateSignature(hashedPayload);
        String encryptedPayload = encryptPayload(jsonPayload, aesKey);

        Map<String, String> body = Map.of("data", encryptedPayload);
        Map<String, String> headers = buildHeaders(aesKey, signature, hashedPayload, true);

        try {
            String response = HttpClientUtil.sendRequest(url, method, body, headers, aesKey);
            if (responseType.equals(String.class)) return (T) response;
            log.info("API Response >>>>>>>>>>>>> " + response);
            return objectMapper.readValue(response, responseType);
        } catch (Exception e) {
            throw new RuntimeException("Error calling " + url, e);
        }

    }

    private Map<String, String> buildHeaders(String aesKey, String signature, String hashedPayload, boolean secured) {
        System.out.println("Building Headers:::::::---------------------- " + aesKey);
        String authHeader = "Bearer " + SHA256.hash(PropertyConfig.getProperty("merchant.public-key"));
        String secureToken = new RSA().encryptWithPublicKey(aesKey, PropertyConfig.getProperty("merchant.vas-public-key"));

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authHeader);
        headers.put("SecureToken", secureToken);
        if (secured && signature != null) headers.put("Signature", signature);

        return headers;
    }

    private String generateSignature(String payloadHash) {
        return new RSA().signWithPrivateKey(payloadHash, PropertyConfig.getProperty("merchant.private-key"));
    }

    private String encryptPayload(String jsonPayload, String aesKeyHex) {
        return AES.encrypt(jsonPayload, aesKeyHex);
    }

    private String generateAesKeyHex() {
        SecureRandom random = new SecureRandom();
        byte[] aesKeyBytes = new byte[32];
        random.nextBytes(aesKeyBytes);

        StringBuilder hexKey = new StringBuilder();
        for (byte b : aesKeyBytes) {
            hexKey.append(String.format("%02x", b));
        }
        return hexKey.toString();
    }

    private String serializeToJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing payload", e);
        }
    }

    public String hashPayload(String jsonPayload) {
        return SHA256.hash(jsonPayload);
    }
}