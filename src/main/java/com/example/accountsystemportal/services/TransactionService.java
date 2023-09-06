package com.example.accountsystemportal.services;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.exceptions.TransactionCreationException;
import com.example.accountsystemportal.exceptions.TransactionNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {

    Transaction createTransaction(Transaction transaction, Long userId) throws TransactionCreationException;

    List<Transaction> viewTransactions() throws TransactionNotFoundException;

    Transaction updateTransactionById(Transaction transaction, Long transactionId) throws TransactionNotFoundException;

    void deleteTransaction(Long transactionId) throws TransactionNotFoundException;
}
