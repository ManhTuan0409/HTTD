package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="categories")
public class ExCategory {
    @Id
    @Column(name="categoryId", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name="categoryName", length = 255)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Expense> expens;
}
