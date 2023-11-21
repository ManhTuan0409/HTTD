package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name="goals")
public class Goal {
    @Id
    @Column(name="goalId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long goalId;
    @Column(name="goalName", length = 255)
    private String goalName;
    @Column(name="goalAmount")
    private float goalAmount;
    @Column(name="currentAmount")
    private float currentAmount;
    @Column(name="startDay")
    private Date startDay;
    @Column(name="endDay")
    private Date endDay;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
}
