package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="incomes")
public class Income {
    @Id
    @Column(name="incomeId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long incomeId;
    @Column(name="incomeName", length = 255)
    private String incomeName;
    @Column(name="amount", length = 255)
    private float amount;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
}
