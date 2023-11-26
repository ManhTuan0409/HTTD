package com.example.HTTD.Dto;

import com.example.HTTD.Entity.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseDto {
    private String name;
    private String description;
    private Float amount;
    private Date date_created;
    private Long categoryId;
}
