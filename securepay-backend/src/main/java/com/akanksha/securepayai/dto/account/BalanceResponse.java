package com.akanksha.securepayai.dto.account;

import com.akanksha.securepayai.enums.AccountType;
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
public class BalanceResponse {
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
}
