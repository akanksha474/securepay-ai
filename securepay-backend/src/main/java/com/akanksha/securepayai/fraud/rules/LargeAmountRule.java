package com.akanksha.securepayai.fraud.rules;

import com.akanksha.securepayai.fraud.FraudContext;
import com.akanksha.securepayai.fraud.FraudRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LargeAmountRule implements FraudRule {
    @Override
    public int calculateRisk(FraudContext fraudcontext){
        if(fraudcontext.getAmount().compareTo(new BigDecimal("50000")) <= 0){
            return 0;
        }
        return 30;
    }
}
