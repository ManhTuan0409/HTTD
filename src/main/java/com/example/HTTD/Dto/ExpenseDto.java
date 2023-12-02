package com.example.HTTD.Dto;

import lombok.*;

import java.util.Date;


@Data
public class ExpenseDto {
    private String name;
    private String description;
    private Float amount;
    private Long categoryId;
    private Date date_created;
    private Long userId;


    private String category;
    private Float totalAmount;

    public ExpenseDto(String category, Float totalAmount) {
    }
}
