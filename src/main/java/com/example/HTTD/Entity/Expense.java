package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @Column(name="expenseId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long expenseId;
    @Column(name="expenseName", length = 255)
    private String expenseName;
    @Column(name="describe", length = 255)
    private String describe;
    @Column(name="amount", length = 255)
    private float amount;
    @Column(name="date_created")
    private Date date_created;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExCategory  category;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
}
