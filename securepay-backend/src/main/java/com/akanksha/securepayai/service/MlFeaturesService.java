package com.akanksha.securepayai.service;

import com.akanksha.securepayai.enums.TransactionType;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.model.Transaction;
import com.akanksha.securepayai.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MlFeaturesService {

    private final TransactionRepository transactionRepository;

    public int extractAccountAge(Account account){
        return (int) ChronoUnit.DAYS.between(
                account.getCreatedAt(),
                LocalDateTime.now()
        );

    }

    public int extractVelocity(Account account){
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);

        List<Transaction> transactions =
                transactionRepository
                        .findByAccountAccountNumberAndTransactionTypeAndTransactionTimeAfter(
                                account.getAccountNumber(),
                                TransactionType.Transfer,
                                oneMinuteAgo
                        );

        return transactions.size();
    }
}
