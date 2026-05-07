package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.app.entity.Transaction;
import com.bank.app.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Deposit
    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Long accountId,
                               @RequestParam Double amount) {
        return transactionService.deposit(accountId, amount);
    }

    // Withdraw
    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Long accountId,
                                @RequestParam Double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    // Transfer
    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam Long fromId,
                                @RequestParam Long toId,
                                @RequestParam Double amount) {
        return transactionService.transfer(fromId, toId, amount);
    }
}