package com.ms.ExpenseService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;

    private

}
