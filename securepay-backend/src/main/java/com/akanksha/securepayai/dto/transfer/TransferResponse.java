package com.akanksha.securepayai.dto.transfer;

import com.akanksha.securepayai.dto.account.BalanceResponse;
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
public class TransferResponse {
    private String senderAccount;
    private String receiverAccount;
    private BigDecimal amount;
    private LocalDate transferDate;
    private LocalDateTime transferTime;
    private String message;
}
