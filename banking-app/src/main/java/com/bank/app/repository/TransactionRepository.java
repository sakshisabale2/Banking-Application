package com.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.app.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountIdOrDestinationAccountId(Long sourceId, Long destId);
}