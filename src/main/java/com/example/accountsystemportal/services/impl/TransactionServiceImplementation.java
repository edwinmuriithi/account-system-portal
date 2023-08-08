package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.repositories.TransactionRepository;
import com.example.accountsystemportal.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImplementation implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction, Long transactionId) {
        transaction.setTransactionType(transaction.getTransactionType());
        transaction.setTransactionAmount(transaction.getTransactionAmount());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> viewTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }

    @Override
    public Transaction updateTransactionById(Transaction transaction, Long transactionId) throws Exception {
        Transaction existingTransaction = transactionRepository.findTransactionById(transactionId).orElseThrow(()-> new Exception("Transaction with ID "+transactionId+" not found"));
        existingTransaction.setTransactionType(transaction.getTransactionType());
        existingTransaction.setTransactionAmount(transaction.getTransactionAmount());
        Transaction newTransaction = transactionRepository.save(existingTransaction);

        return newTransaction;
    }
}
