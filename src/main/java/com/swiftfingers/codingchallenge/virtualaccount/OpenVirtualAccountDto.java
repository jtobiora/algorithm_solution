package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.Data;

import java.util.Map;

/**
 * Created by Obiora on 14-Jul-2025 at 09:47
 */
@Data
public class OpenVirtualAccountDto {
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

