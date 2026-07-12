package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.account.WithdrawRequest;
import com.akanksha.securepayai.dto.account.WithdrawResponse;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.exception.InsufficientBalanceException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.repository.AccountRepository;
import com.akanksha.securepayai.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class WithdrawService {
    private final AccountRepository accountRepository;

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
        response.setTransactionTime(LocalDateTime.now());
        response.setMessage("Withdrawal successful");
        return response;
    }


}
