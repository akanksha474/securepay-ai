package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.transfer.TransferRequest;
import com.akanksha.securepayai.dto.transfer.TransferResponse;
import com.akanksha.securepayai.enums.RiskLevel;
import com.akanksha.securepayai.enums.TransactionDirection;
import com.akanksha.securepayai.enums.TransactionType;
import com.akanksha.securepayai.exception.AccountNotFoundException;
import com.akanksha.securepayai.exception.FraudDetectionException;
import com.akanksha.securepayai.exception.MinimumBalanceException;
import com.akanksha.securepayai.exception.SameAccountTransferException;
import com.akanksha.securepayai.fraud.FraudContext;
import com.akanksha.securepayai.fraud.FraudDetectionService;
import com.akanksha.securepayai.fraud.FraudResult;
import com.akanksha.securepayai.model.Account;
import com.akanksha.securepayai.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransactionHistoryService transactionHistoryService;
    private final FraudDetectionService fraudDetectionService;



    public TransferResponse transferMoney(TransferRequest transferRequest) {
        Account senderAccount = accountRepository.findByAccountNumber(transferRequest.getSenderAccountNumber()).orElseThrow(()-> new AccountNotFoundException("Account Not Found"));
        Account receiverAccount = accountRepository.findByAccountNumber(transferRequest.getReceiverAccountNumber()).orElseThrow(()-> new AccountNotFoundException("Account Not Found"));
        if(senderAccount.getAccountNumber().equals(receiverAccount.getAccountNumber())) {
            throw new SameAccountTransferException("Sender account and receiver account are the same");
        }
        if ((senderAccount.getBalance()
                .subtract(transferRequest.getAmount()))
                .compareTo(new BigDecimal("500")) < 0) {
            throw new MinimumBalanceException("Minimum balance must be greater or equal to 500");

        }

        // fraud detection
        FraudContext fraudContext = FraudContext.builder().account(senderAccount).amount(transferRequest.getAmount()).transactionTime(LocalDateTime.now()).build();
        FraudResult fraudResult = fraudDetectionService.calculateRisk(fraudContext);
        // tested the logics
         System.out.println("Risk score: " + fraudResult.getRiskScore());
        System.out.println("Risk Level:"+ fraudResult.getRiskLevel());

        if(fraudResult.getRiskLevel() == RiskLevel.HIGH){
            throw new FraudDetectionException("High Risk : transaction blocked, Needs to verify the Transaction!");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(transferRequest.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferRequest.getAmount()));

        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setSenderAccount(senderAccount.getAccountNumber());
        transferResponse.setReceiverAccount(receiverAccount.getAccountNumber());
        transferResponse.setAmount(transferRequest.getAmount());
        transferResponse.setTransferDate(LocalDate.now());
        transferResponse.setTransferTime(LocalDateTime.now());
        transferResponse.setMessage("Transfer Successful");
        transactionHistoryService.saveTransaction(senderAccount,transferRequest.getAmount(), TransactionType.Transfer, TransactionDirection.Debit);
        transactionHistoryService.saveTransaction(receiverAccount,transferRequest.getAmount(), TransactionType.Transfer, TransactionDirection.Credit);
        return transferResponse;
    }
}
