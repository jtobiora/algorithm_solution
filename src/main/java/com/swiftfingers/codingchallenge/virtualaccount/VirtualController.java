package com.swiftfingers.codingchallenge.virtualaccount;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Obiora on 14-Jul-2025 at 09:55
 */
@RequestMapping("/api/virtual")
@RestController
@RequiredArgsConstructor
@Slf4j
public class VirtualController {

    private final VirtualAccountService service;

    @GetMapping("/accounts")
    public ResponseEntity<?> getVirtualAccounts () {
        try{
            VirtualAccountsResponseDto allVirtualAccounts = service.getAllVirtualAccounts();
            return ResponseEntity.ok(allVirtualAccounts);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @PostMapping("/open-static-account")
    public ResponseEntity<?> openStaticAccount (@RequestBody OpenVirtualAccountDto openVirtualAccountDto) {
        VirtualAccountCreateResponseDto virtualAccountCreateResponseDto = service.openVirtualAccount(openVirtualAccountDto);
        return ResponseEntity.ok(virtualAccountCreateResponseDto);
    }
    @PostMapping("/open-dynamic-account")
    public ResponseEntity<?> openDynamicAccount (@RequestBody OpenVirtualAccountDto openVirtualAccountDto) {
        VirtualAccountCreateResponseDto virtualAccountCreateResponseDto = service.openVirtualAccount(openVirtualAccountDto);
        return ResponseEntity.ok(virtualAccountCreateResponseDto);
    }

    @PostMapping("/transaction-notification-webhook")
    public ResponseEntity<?> getTransactionNotification (@RequestBody String payload, HttpServletRequest request) {
        try {
            TransactionNotificationResponseDto transactionNotificationWebhook = service.getTransactionNotificationWebhook(payload, request);
            return ResponseEntity.ok(transactionNotificationWebhook);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @PostMapping("/open-account")
    public ResponseEntity<?> openVirtualAccount () {
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
        request.setMerchant(merchant);

        VirtualAccountCreateResponseDto virtualAccount = service.openVirtualAccount(request);
        System.out.println("Created Virtual Account >>>> " + virtualAccount);

        return null;
    }
}
