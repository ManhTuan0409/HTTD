package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Data
@Table(name="wallet")
public class Wallet {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name", length = 255)
    private String name;
    @Column(name="amount", length = 255)
    private Float amount;
    @Column(name="date_created")
    private Date date_created;
}
