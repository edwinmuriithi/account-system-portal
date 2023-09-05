package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.exceptions.UserNotFoundException;
import com.example.accountsystemportal.repositories.TransactionRepository;
import com.example.accountsystemportal.repositories.UserRepository;
import com.example.accountsystemportal.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImplementation implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction, Long userId) {
        User transactionUpload = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID "+ userId+" not found"));
        transaction.setTransactionType(transaction.getTransactionType());
        transaction.setTransactionAmount(transaction.getTransactionAmount());
        transaction.setUser(transactionUpload);
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

    @Override
    public void deleteTransaction(Long transactionId) throws Exception {
        transactionRepository.findById(transactionId).orElseThrow(()-> new Exception("transaction with id "+transactionId+ " not found"));
        log.info("Successfully deleted transaction");
        transactionRepository.deleteById(transactionId);
    }
}
