package com.ms.expenseService.controller;

import com.ms.expenseService.dto.ExpenseDto;
import com.ms.expenseService.entities.Expense;
import com.ms.expenseService.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/expense/v1/getExpenses/{userId}")
    public ResponseEntity<List<ExpenseDto>> getExpenses(@PathVariable String userId)
    {
        try
        {
            List<ExpenseDto> allExpenses = expenseService.getExpenses(userId);
            return new ResponseEntity<>(allExpenses, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/expense/v1/createExpense")
    public ResponseEntity<> createExpense(@RequestBody ExpenseDto expenseDto)
    {
        Boolean flag = expenseService.createExpense(expenseDto);
        if(flag)
            return new ResponseEntity<>(flag, HttpStatus.OK);
        else
            return new ResponseEntity<>(flag,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
