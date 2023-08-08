package com.example.accountsystemportal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transactionType;
    private Integer transactionAmount;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate transactionDate;

    @PrePersist
    private void onCreate(){
        transactionDate =  LocalDate.now();
    }
}
