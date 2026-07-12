package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.account.*;
import com.akanksha.securepayai.service.AccountService;
import com.akanksha.securepayai.service.DepositService;
import com.akanksha.securepayai.service.WithdrawService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request){

        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> depositMoney(@Valid @RequestBody DepositRequest request){
        DepositResponse response = depositService.depositMoney(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponse> withdrawMoney(@Valid @RequestBody WithdrawRequest request ){
        WithdrawResponse response = withdrawService.withdrawMoney(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
