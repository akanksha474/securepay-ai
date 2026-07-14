package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.transaction.TransactionResponse;
import com.akanksha.securepayai.enums.TransactionDirection;
import com.akanksha.securepayai.enums.TransactionType;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.exception.TransactionNotFoundException;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.model.Transaction;
import com.akanksha.securepayai.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService {
    private final TransactionRepository transactionRepository;

    public List<TransactionResponse> getTransactionHistory(String accountNumber){
        List<Transaction> transactions = transactionRepository.findByAccountAccountNumberOrderByTransactionDateDesc(accountNumber);
        if(transactions.isEmpty()){
            throw new TransactionNotFoundException("No transactions found");
        }

        return transactions.stream().map(transaction -> {
            TransactionResponse response = new TransactionResponse();
            response.setTransactionId(transaction.getTransactionId());
            response.setTransactionDate(transaction.getTransactionDate());
            response.setTransactionTime(transaction.getTransactionTime());
            response.setTransactionType(transaction.getTransactionType());
            response.setAmount(transaction.getAmount());
            response.setTransactionStatus(transaction.getTransactionStatus());
            response.setTransactionDirection(transaction.getTransactionDirection());
            return response;
        }).toList() ;
    }

    public void saveTransaction(Account account, BigDecimal amount, TransactionType transactionType, TransactionDirection transactionDirection){
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setAccount(account);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDirection(transactionDirection);
        transaction.setAmount(amount);
        transaction.setTransactionStatus("SUCCESSFUL");
        transactionRepository.save(transaction);
    }

}
