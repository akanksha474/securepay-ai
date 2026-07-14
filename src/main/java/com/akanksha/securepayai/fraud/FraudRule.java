package com.akanksha.securepayai.fraud;

import com.akanksha.securepayai.model.Account;

import java.math.BigDecimal;

public interface FraudRule {
    int calculateRisk(FraudContext fraudContext);
}
