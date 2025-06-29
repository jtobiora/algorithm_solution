package com.swiftfingers.codingchallenge.docstest;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Created by Obiora on 20-Jun-2025 at 10:11
 */
@Data
@Builder
public class UpdateVirtualAccountRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String accountName;
    private String productType;
    private Map<String, String> meta;
}
