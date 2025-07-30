package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by Obiora on 14-Jul-2025 at 09:45
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualAccountsResponseDto {
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

        private String provider;

        @JsonProperty("provider_response")
        private ProviderResponse providerResponse;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProviderResponse {
        private List<VirtualAccount> virtualAccounts;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VirtualAccount {
        private String accountName;
        private String type;
        private String productType;
        private double fee;
        private String status;
        private Map<String, Object> meta;
        private String customerReference;
        private String createdAt;
        @JsonProperty("vNUBAN")
        private String vNUBAN;
    }
}

