package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.account.WithdrawRequest;
import com.akanksha.securepayai.dto.account.WithdrawResponse;
import com.akanksha.securepayai.enums.TransactionDirection;
import com.akanksha.securepayai.enums.TransactionType;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.exception.InsufficientBalanceException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.model.Transaction;
import com.akanksha.securepayai.repository.AccountRepository;
import com.akanksha.securepayai.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class WithdrawService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionHistoryService transactionHistoryService;

    public WithdrawResponse withdrawMoney(WithdrawRequest request){
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(account.getBalance().compareTo(request.getAmount())<0){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        WithdrawResponse response = new WithdrawResponse();
        response.setPreviousBalance(account.getBalance());

        account.setBalance((account.getBalance().subtract(request.getAmount())));

        response.setAccountNumber(account.getAccountNumber());
        response.setRemainingBalance(account.getBalance());
        response.setWithdrawalAmount(request.getAmount());
        response.setTransactionDate(LocalDate.now());
        response.setTransactionTime(LocalDateTime.now());
        response.setMessage("Withdrawal successful");
        transactionHistoryService.saveTransaction(account,request.getAmount(),TransactionType.Withdrawal, TransactionDirection.Debit);
        return response;
    }



}
