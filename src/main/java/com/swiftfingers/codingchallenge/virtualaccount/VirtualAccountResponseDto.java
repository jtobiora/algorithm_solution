package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Obiora on 14-Jul-2025 at 09:46
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualAccountResponseDto {
    private String status;
    private String message;
    private DataContent data;
    private Object error;
    private Object errors;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataContent {
        @JsonProperty("provider_response_code")
        private String providerResponseCode;

        private String provider;

        @JsonProperty("provider_response")
        private ProviderResponse providerResponse;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProviderResponse {
        @JsonProperty("virtualAccount")
        private VirtualAccount virtualAccount;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VirtualAccount {
        private String accountName;
        private String type;
        private String productType;
        private double fee;
        private String status;
        private String meta;
        private String customerReference;
        private String createdAt;
        @JsonProperty("vNUBAN")
        private String vnuban;
    }
}