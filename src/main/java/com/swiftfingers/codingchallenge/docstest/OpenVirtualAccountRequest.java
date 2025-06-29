package com.swiftfingers.codingchallenge.docstest;

import lombok.Data;

import java.util.Map;

/**
 * Created by Obiora on 13-Jun-2025 at 12:57
 */
@Data
public class OpenVirtualAccountRequest {
    private String bvn;
    private String firstName;
    private String lastName;
    private String middleName;
    private String accountName;
    private String email;
    private String phone;
    private String productType;
    private String customerReference;
    private String expireAt;
    private String singleDepositLimit;
    private Merchant merchant;
    private String status;
    private Map<String, String> meta;

    @Data
    public static class Merchant {
        private String code;
    }
}