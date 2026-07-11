package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.account.AccountRequest;
import com.akanksha.securepayai.dto.account.AccountResponse;
import com.akanksha.securepayai.dto.account.DepositRequest;
import com.akanksha.securepayai.dto.account.DepositResponse;
import com.akanksha.securepayai.service.AccountService;
import com.akanksha.securepayai.service.DepositService;
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

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request){

        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@Valid @RequestBody DepositRequest request){
        DepositResponse response = depositService.depositMoney(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
