package com.example.HTTD.Service;

import com.example.HTTD.Entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(Expense expense);

    Expense getExpenseById(Long expenseId);

    List<Expense> getAllExpense();

    Expense updateExpense(Expense expense);

    boolean deleteExpense(Long expenseId);
}
