package com.example.HTTD.Repository;

import com.example.HTTD.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategory extends JpaRepository<Expense, Long> {
}
