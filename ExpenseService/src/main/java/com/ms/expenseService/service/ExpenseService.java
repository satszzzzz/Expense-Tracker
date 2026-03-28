package com.ms.expenseService.service;

import com.ms.expenseService.dto.ExpenseDto;
import com.ms.expenseService.entities.Expense;
import com.ms.expenseService.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ObjectMapper objectMapper;

    public boolean createExpense(ExpenseDto expenseDto)
    {
        try{
            expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateExpense(ExpenseDto expenseDto)
    {
        Optional<Expense> expenseToBeUpdated = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(), expenseDto.getExternalId());
        if(expenseToBeUpdated.isEmpty())
            return false;
        Expense expense = expenseToBeUpdated.get();
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())?expenseDto.getCurrency():expense.getCurrency());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())? expenseDto.getMerchant() : expense.getMerchant());
        expense.setAmount(expenseDto.getAmount());
        expenseRepository.save(expense);
        return true;
    }

    public List<ExpenseDto> getExpenses(String userId)
    {
        List<Expense> allExpenses = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(allExpenses, new TypeReference<List<ExpenseDto>>() {});
    }

    private void setCurrency(ExpenseDto expenseDto)
    {
        if(Objects.isNull(expenseDto.getCurrency()))
            expenseDto.setCurrency("INR");
    }
}
