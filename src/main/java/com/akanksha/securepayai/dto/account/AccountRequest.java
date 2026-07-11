package com.akanksha.securepayai.dto.account;

import com.akanksha.securepayai.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private AccountType accountType;
    @NotNull
    @Positive
    private BigDecimal balance;

}
