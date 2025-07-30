package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Obiora on 14-Jul-2025 at 14:25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionInflowQueryDto {
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
        private List<TransactionNotificationResponseDto> data;

    }
}