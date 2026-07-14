package com.akanksha.securepayai.fraud.rules;

import com.akanksha.securepayai.enums.TransactionType;
import com.akanksha.securepayai.fraud.FraudContext;
import com.akanksha.securepayai.fraud.FraudRule;
import com.akanksha.securepayai.model.Transaction;
import com.akanksha.securepayai.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VelocityFraudRule implements FraudRule {
    private final TransactionRepository transactionRepository;

    @Override
    public int calculateRisk(FraudContext fraudContext){
        LocalDateTime oneMinuteAgo  = fraudContext.getTransactionTime().minusMinutes(1);
        List<Transaction> transactions  = transactionRepository.findByAccountAccountNumberAndTransactionTypeAndTransactionTimeAfter(fraudContext.getAccount().getAccountNumber(), TransactionType.Transfer,oneMinuteAgo);
         int transactionCount = transactions.size();
         if(transactionCount >= 3){
             return 40;
         }
        System.out.println("transactionCount: " + transactionCount);
         return 0;
    }

}
