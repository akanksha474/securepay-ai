package com.akanksha.securepayai.model;

import com.akanksha.securepayai.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "all_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionStatus;
    @ManyToOne
    @JoinColumn(name = "account_account_id")
    private Account account;
    private BigDecimal amount;
    private LocalDate transactionDate;
    private LocalDateTime transactionTime;


}
