package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Created by Obiora on 14-Jul-2025 at 09:54
 */
@Slf4j
public class AppTester {

    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        // VirtualAccountService service = new VirtualAccountService();

        //-------------------------- GET ALL VIRTUAL ACCOUNTS ------------------------
//        VirtualAccountsResponseDto allVirtualAccounts = service.getAllVirtualAccounts();
//        System.out.println("ALl virtual accounts " + allVirtualAccounts);


        //-------------------------- GET VIRTUAL ACCOUNT BY NUMBER -------------------
//        VirtualAccountResponseDto virtualAccount = service.getVirtualAccount("6200023771");
//        System.out.println("Virtual account response " + virtualAccount);


//        -------------------------- OPEN VIRTUAL ACCOUNT ----------------------------
        OpenVirtualAccountDto request = new OpenVirtualAccountDto();
        request.setBvn("5436276543");
        request.setFirstName("Femi");
        request.setLastName("Adebayo");
        request.setMiddleName("Shola");
        request.setAccountName("Afolabi Margareth Uzzih");
        request.setEmail("afolabi.uzzih@gmail.com");
        request.setPhone("2348066552776");
        request.setProductType("ABC");
        request.setCustomerReference(UUID.randomUUID().toString().substring(0,12));
        request.setExpireAt(null);
        request.setSingleDepositLimit("100");

        OpenVirtualAccountDto.Merchant merchant = new OpenVirtualAccountDto.Merchant();
        merchant.setCode("E5EC4");
//        request.setMerchant(merchant);
//
//        VirtualAccountCreateResponseDto virtualAccount = service.openVirtualAccount(request);
//        System.out.println("Created Virtual Account >>>> " + virtualAccount);



        //------------------------------ UPDATE VIRTUAL ACCOUNT -------------------------------------------
//        UpdateVirtualAccountDto updateRequest = UpdateVirtualAccountDto.builder()
//                .firstName("Thomas")
//                .middleName("Oladele")
//                .lastName("Akinbola")
//                .accountName("Thomas Finney Anthony")
//                .productType("ABC")
//                .meta(null)
//                .build();
//
//
//        VirtualAccountUpdateResponseDto updated = service.updateVirtualAccount(updateRequest, "6200023805");
//        System.out.println("Updated Virtual account " + updated);



        //---------------------------- TRANSFER FUNDS ------------------------------
//        FundsTransferDto transferFunds = new FundsTransferDto();
//        transferFunds.setNarration("Transfer test");
//        transferFunds.setDestinationAccount("2096763285");
//        transferFunds.setSourceAccount("1019464009");
//        transferFunds.setTransferAmount("100");
//        transferFunds.setTransactionRef(UUID.randomUUID().toString().substring(0,12));
//        transferFunds.setDestinationAccountName("MAURICE UBOK-UDOM");
//        transferFunds.setDestinationBankCode("060");
//
//        FundsTransferResponseDto fundsTransferResponseDto = service.transferFunds(transferFunds);



        //--------------------- REQUERY ----------------------------
//        RequeryDto request = new RequeryDto();
//        request.setTransactionRef("4113d42d-bb8");
//        request.setAccountNumber("2096763285");
//        request.setStan("525871609515");
//
//        service.requeryTransferStatus(request);
    }
}
