package com.swiftfingers.codingchallenge.virtualaccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Obiora on 14-Jul-2025 at 10:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionNotificationResponseDto {
    private String sessionID;
    private String nameEnquiryRef;
    private String destinationInstitutionCode;
    private String channelCode;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNumber;
    private String beneficiaryBankVerificationNumber;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String originatorBankVerificationNumber;
    private String originatorKYCLevel;
    private String transactionLocation;
    private String narration;
    private String paymentReference;
    private String status;
    private String amount;
    private String collectionAccountNumber;
    private String createdOn;
    private String updatedOn;
}
