package com.example.HTTD.Service;

import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Entity.Wallet;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(Expense expense);

    Expense getExpenseById(Long expenseId);

    List<Expense> getAllExpense();

    Expense updateExpense(Expense expense);

    void deleteExpense(Long expenseId);
}
