package com.akanksha.securepayai.dto.account;

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
public class DepositResponse {

    private String accountNumber;
    private BigDecimal previousBalance;
    private BigDecimal depositAmount;
    private BigDecimal newBalance;
    private LocalDate transactionDate;
    private LocalDateTime transactionTime;
    private String message;

}
