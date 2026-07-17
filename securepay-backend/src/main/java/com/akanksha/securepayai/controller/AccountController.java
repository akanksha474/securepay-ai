package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.account.*;
import com.akanksha.securepayai.dto.transfer.TransferRequest;
import com.akanksha.securepayai.dto.transfer.TransferResponse;
import com.akanksha.securepayai.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final BalanceService balanceService;
    private final TransferService transferService;

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
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountNumber){
        BalanceResponse response = balanceService.getBalance(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferMoney(@Valid @RequestBody TransferRequest request){
        TransferResponse response = transferService.transferMoney(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
