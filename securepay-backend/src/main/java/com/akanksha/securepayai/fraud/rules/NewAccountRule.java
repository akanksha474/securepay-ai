package com.akanksha.securepayai.fraud.rules;

import com.akanksha.securepayai.fraud.FraudContext;
import com.akanksha.securepayai.fraud.FraudRule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class NewAccountRule implements FraudRule {

    @Override
    public int calculateRisk(FraudContext fraudContext) {
        LocalDateTime createdAt = fraudContext.getAccount().getCreatedAt();
        LocalDateTime currentDate = LocalDateTime.now();
        long daysSinceCreation = ChronoUnit.DAYS.between(createdAt, currentDate);
        if(daysSinceCreation <= 7){
            return 20;
        }
        return 0;
    }
}
