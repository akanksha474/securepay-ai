package com.akanksha.securepayai.dto.account;

import jakarta.validation.constraints.NotBlank;
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
public class WithdrawRequest {
    @NotBlank(message = "Account Number is required")
    private String accountNumber;
    @NotNull
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
