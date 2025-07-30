package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.Data;

/**
 * Created by Obiora on 14-Jul-2025 at 09:50
 */
@Data
public class RequeryDto {
    private String transactionRef;
    private String accountNumber;
    private String stan;
}

