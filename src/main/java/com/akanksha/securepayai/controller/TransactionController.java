package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.transaction.TransactionResponse;
import com.akanksha.securepayai.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionHistoryService transactionHistoryService;

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@PathVariable String accountNumber){
        List<TransactionResponse> transactionHistory = transactionHistoryService.getTransactionHistory(accountNumber);
        return ResponseEntity.ok(transactionHistory);
    }

}
