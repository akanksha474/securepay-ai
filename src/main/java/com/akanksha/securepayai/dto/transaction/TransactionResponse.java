package com.akanksha.securepayai.dto.transaction;

import com.akanksha.securepayai.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long transactionId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String transactionStatus;
    private LocalDate transactionDate;
    private LocalDateTime transactionTime;

}
