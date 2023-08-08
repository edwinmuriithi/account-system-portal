package com.example.accountsystemportal.controller;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.dtos.TransactionDTO;
import com.example.accountsystemportal.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionDTO> createExpenditure(@RequestBody TransactionDTO transactionDTO, @PathVariable(value = "userId") Long userId){
        Transaction transactionRequest = modelMapper.map(transactionDTO,Transaction.class);
        Transaction transaction = transactionService.createTransaction(transactionRequest,userId);
        TransactionDTO transactionResponse = modelMapper.map(transaction, TransactionDTO.class);
        log.info("Transaction is {}",transactionResponse);
        return new ResponseEntity<TransactionDTO>(transactionResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> viewAll(){

        return ResponseEntity.ok(transactionService.viewTransactions().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList()));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> updateTransactionById(@RequestBody TransactionDTO transactionDTO, @PathVariable Long transactionId)
            throws Exception {
        Transaction transactionRequest = modelMapper.map(transactionDTO, Transaction.class);
        Transaction transaction = transactionService.updateTransactionById(transactionRequest, transactionId);
        TransactionDTO transactionResponse = modelMapper.map(transaction,TransactionDTO.class);
        return ResponseEntity.ok().body(transactionResponse);
           }


    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) throws Exception {
        log.info("Transaction has been deleted successfully");
        transactionService.deleteTransaction(transactionId);
    }

}
