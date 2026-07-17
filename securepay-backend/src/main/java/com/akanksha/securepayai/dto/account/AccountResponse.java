package com.akanksha.securepayai.dto.account;

import com.akanksha.securepayai.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String customerName;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;

}
