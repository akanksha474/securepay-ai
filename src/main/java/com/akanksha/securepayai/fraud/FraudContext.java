package com.akanksha.securepayai.fraud;

import com.akanksha.securepayai.model.Account;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudContext {
    private Account account;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
}
