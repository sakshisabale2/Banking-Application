package com.bank.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // 💰 Deposit
    @Transactional
    public Transaction deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setTransactionType("DEPOSIT");
        tx.setStatus("SUCCESS");
        tx.setDestinationAccount(account);

        return transactionRepository.save(tx);
    }

    // 💸 Withdraw
    @Transactional
    public Transaction withdraw(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setTransactionType("WITHDRAW");
        tx.setStatus("SUCCESS");
        tx.setSourceAccount(account);

        return transactionRepository.save(tx);
    }

    // 🔥 Transfer (VERY IMPORTANT)
    @Transactional
    public Transaction transfer(Long fromId, Long toId, Double amount) {

        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setTransactionType("TRANSFER");
        tx.setStatus("SUCCESS");
        tx.setSourceAccount(from);
        tx.setDestinationAccount(to);

        return transactionRepository.save(tx);
    }
}