package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.Data;

/**
 * Created by Obiora on 14-Jul-2025 at 09:51
 */
@Data
public class FundsTransferDto {
    private String transactionRef;
    private String sourceAccount;
    private String destinationAccount;
    private String transferAmount;
    private String narration;
    private String destinationAccountName;
    private String destinationBankCode;
}