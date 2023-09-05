package com.example.accountsystemportal.services;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {

    Transaction createTransaction(Transaction transaction, Long userId);

    List<Transaction> viewTransactions() ;

    Transaction updateTransactionById(Transaction transaction, Long transactionId) throws Exception;

    void deleteTransaction(Long transactionId) throws Exception;
}
