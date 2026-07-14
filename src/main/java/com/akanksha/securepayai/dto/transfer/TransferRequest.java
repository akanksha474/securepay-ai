package com.akanksha.securepayai.dto.transfer;

import com.akanksha.securepayai.enums.AccountType;
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
public class TransferRequest {
    @NotBlank(message = "Account Number is required")
    private String senderAccountNumber;
    @NotBlank(message = "Account Number is required")
    private String receiverAccountNumber;

    @Positive
    @NotNull
    private BigDecimal amount;
}
