package com.example.accountsystemportal.entities.dtos;

import com.example.accountsystemportal.entities.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {


    private Long id;
    private String fname;
    private LocalDate dob;
    private String accountType;
    private Integer balance;

    private List<Transaction> transactionList;
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate createdDate;

}
