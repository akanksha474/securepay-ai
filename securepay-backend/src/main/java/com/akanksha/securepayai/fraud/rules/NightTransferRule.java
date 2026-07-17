package com.akanksha.securepayai.fraud.rules;

import com.akanksha.securepayai.fraud.FraudContext;
import com.akanksha.securepayai.fraud.FraudRule;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class NightTransferRule implements FraudRule {

    @Override
    public int calculateRisk(FraudContext fraudContext){
       LocalTime time = LocalTime.from(fraudContext.getTransactionTime());
       if(!time.isBefore(LocalTime.MIDNIGHT)&&time.isBefore(LocalTime.of(5,0))) {
           return 20;
       }
       return 0;

    }
}
