package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Repository.ExpenseRepository;
import com.example.HTTD.Service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    ExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
        Optional<Expense> optional = expenseRepository.findById(expenseId);
        return optional.orElse(null);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense updateExpense(Expense expense) {
        Expense existingexpense = expenseRepository.findById(expense.getId()).get();
        existingexpense.setName(expense.getName());
        existingexpense.setDescription(expense.getDescription());
        existingexpense.setAmount(expense.getAmount());
        existingexpense.setDate_created(expense.getDate_created());
        Expense updatedexpense = expenseRepository.save(existingexpense);
        return updatedexpense;
    }

    @Override
    public boolean deleteExpense(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isPresent()) {
            expenseRepository.deleteById(expenseId);
            return true;
        }
        return false;
    }
}
