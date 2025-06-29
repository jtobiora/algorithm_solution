package com.swiftfingers.codingchallenge.docstest;

import lombok.Data;

/**
 * Created by Obiora on 16-Jun-2025 at 16:43
 */
@Data
public class TransferFunds {
    private String transactionRef;
    private String sourceAccount;
    private String destinationAccount;
    private String transferAmount;
    private String narration;
    private String destinationAccountName;
    private String destinationBankCode;
}
