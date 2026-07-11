package com.akanksha.securepayai.dto;

import com.akanksha.securepayai.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private BigDecimal balance;

}
