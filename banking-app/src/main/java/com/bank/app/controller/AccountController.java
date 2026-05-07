package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.app.entity.Account;
import com.bank.app.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create Account
    @PostMapping("/create/{userId}")
    public Account createAccount(@PathVariable Long userId,@RequestBody Account account) {
        return accountService.createAccount(userId, account);
    }

    // Get Accounts by User
    @GetMapping("/user/{userId}")
    public List<Account> getUserAccounts(@PathVariable Long userId) {
        return accountService.getUserAccounts(userId);
    }
}