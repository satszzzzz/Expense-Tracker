package com.ms.expenseService.repository;

import com.ms.expenseService.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndCreatedAt(String userId, Timestamp createdAt);

    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
}
