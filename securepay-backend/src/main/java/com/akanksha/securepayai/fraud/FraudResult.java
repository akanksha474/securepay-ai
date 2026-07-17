package com.akanksha.securepayai.fraud;

import com.akanksha.securepayai.enums.RiskLevel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudResult {
    private int riskScore;
    private RiskLevel riskLevel;
}
