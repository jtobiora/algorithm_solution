package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Obiora on 14-Jul-2025 at 09:49
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundsTransferResponseDto {
    private String status;
    private String message;
    private ResponseData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        @JsonProperty("provider_response_code")
        private String providerResponseCode;

        private String provider;

        @JsonProperty("provider_response")
        private ProviderResponse providerResponse;

        private Object error;
        private Object errors;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProviderResponse {
        private TransactionData data;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TransactionData {
        private String transactionRef;
        private String status;
        private String responseCode;
        private String narration;
        private String responseMessage;
        private String bankResponse;
        private String codeMessage;
        private String paymentReference;
    }
}

