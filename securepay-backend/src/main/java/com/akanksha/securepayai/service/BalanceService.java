package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.account.BalanceResponse;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountRepository accountRepository;
    public BalanceResponse getBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new AccountNotFoundException("Account Not Found!"));
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountType(account.getAccountType());
        balanceResponse.setAccountNumber(account.getAccountNumber());
        balanceResponse.setBalance(account.getBalance());
        return balanceResponse;
    }
}
