package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="debts")
public class Debt {
    @Id
    @Column(name="debtId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long debtId;
    @Column(name="debtName", length = 255)
    private String debtName;
    @Column(name="debtAmount")
    private float debtAmount;

    @Column(name="startDay")
    private Date startDay;
    @Column(name="endDay")
    private Date endDay;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
}
