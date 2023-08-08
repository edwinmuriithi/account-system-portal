package com.example.accountsystemportal.entities.dtos;

import com.example.accountsystemportal.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {

    private String transactionType;
    private Integer transactionAmount;
    private LocalDate transactionDate;

}
