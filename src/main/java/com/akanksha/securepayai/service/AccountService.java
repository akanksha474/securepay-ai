package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.account.AccountRequest;
import com.akanksha.securepayai.dto.account.AccountResponse;
import com.akanksha.securepayai.exception.AccountAlreadyExistsException;
import com.akanksha.securepayai.exception.CustomerNotFoundException;
import com.akanksha.securepayai.exception.MinimumBalanceException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.model.Customer;
import com.akanksha.securepayai.repository.AccountRepository;
import com.akanksha.securepayai.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {
    public final CustomerRepository customerRepository;
    public final AccountRepository accountRepository;
    public AccountResponse createAccount(AccountRequest request){
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        boolean exits = accountRepository.existsByCustomerAndAccountType(customer,request.getAccountType());
        if(exits){
            throw new AccountAlreadyExistsException("Account already exists");
        }
        if(request.getBalance().compareTo(new BigDecimal("500"))<0){
            throw new MinimumBalanceException("Balance must be greater than 500");
        }
        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(request.getBalance());
        account.setAccountType(request.getAccountType());
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setCustomerName(customer.getCustomerName());
        accountResponse.setBalance(request.getBalance());
        accountResponse.setAccountType(request.getAccountType());

        String accountNumber = generateAccountNumber(account.getAccountId());
        account.setAccountNumber(accountNumber);
        accountRepository.save(account);
        accountResponse.setAccountNumber(accountNumber);
        return accountResponse;
    }

    public String generateAccountNumber(Long accountId){
        return "ACC"+"1000"+accountId.toString();
    }

}
