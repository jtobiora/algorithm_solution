package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Created by Obiora on 14-Jul-2025 at 09:49
 */
@Data
@Builder
public class UpdateVirtualAccountDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String accountName;
    private String productType;
    private Map<String, String> meta;
}