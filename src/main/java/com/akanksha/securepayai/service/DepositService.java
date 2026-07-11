package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.DepositRequest;
import com.akanksha.securepayai.dto.DepositResponse;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.exception.InvalidDepositAmountException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DepositService {
    private final AccountRepository accountRepository;

    public DepositResponse depositMoney(DepositRequest request){
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(request.getAmount().compareTo(new BigDecimal("100"))<0){
            throw new InvalidDepositAmountException("Minimum deposit amount is 100");
        }
        DepositResponse response = new DepositResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setPreviousBalance(account.getBalance());
        account.setBalance(account.getBalance().add(request.getAmount()));
        // no need to use save - accountRepository.save(account); becoz @transactional will do dirty checking and update the database
        response.setNewBalance(account.getBalance());
        response.setDepositAmount(request.getAmount());
        response.setTransactionTime(LocalDateTime.now());
        response.setMessage("Deposit successful");
        return response;
    }

}