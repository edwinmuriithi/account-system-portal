package com.example.accountsystemportal.repositories;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findTransactionById(Long transactionId);
}
