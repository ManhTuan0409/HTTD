package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Data
@Table(name="wallets")
public class Wallet {
    @Id
    @Column(name="wallId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wallId;
    @Column(name="wallName", length = 255)
    private String wallName;
    @Column(name="amount", length = 255)
    private float amount;
    @Column(name="date_created")
    private Date date_created;
}
