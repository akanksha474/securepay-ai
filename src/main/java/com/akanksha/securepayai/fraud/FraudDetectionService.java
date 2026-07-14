package com.akanksha.securepayai.fraud;

import com.akanksha.securepayai.enums.RiskLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudDetectionService {
    private final List<FraudRule> fraudRules;

    public FraudDetectionService(List<FraudRule> fraudRules) {
        this.fraudRules = fraudRules;
    }

    public FraudResult calculateRisk(FraudContext fraudContext){
        int totalRisk = 0;
        RiskLevel riskLevel;
        for (FraudRule rule: fraudRules){
            totalRisk += rule.calculateRisk(fraudContext);
        }

        if(totalRisk <=30){
            riskLevel = RiskLevel.LOW;
        } else if (totalRisk <= 60) {
            riskLevel = RiskLevel.MEDIUM;
        } else {
            riskLevel = RiskLevel.HIGH;
        }

        return FraudResult.builder().riskScore(totalRisk).riskLevel(riskLevel).build();
    }
}
