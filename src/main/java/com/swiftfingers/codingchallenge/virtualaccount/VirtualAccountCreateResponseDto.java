package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Obiora on 14-Jul-2025 at 09:48
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualAccountCreateResponseDto {
    private String status;
    private String message;
    private ResponseData data;
    private Object error;
    private Object errors;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        @JsonProperty("provider_response_code")
        private String providerResponseCode;

        private Object provider;

        @JsonProperty("provider_response")
        private ProviderResponse providerResponse;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProviderResponse {
        private VirtualAccount virtualAccount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VirtualAccount {
        private String accountName;
        private String type;
        private String productType;
        private double fee;
        private String status;
        private Object meta;
        private String customerReference;
        private String createdAt;

        @JsonProperty("vNUBAN")
        private String vNUBAN;
    }
}