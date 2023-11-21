package com.example.HTTD.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class WalletDto {
    private long wallId;
    private String wallName;
    private float amount;
    private Date date_created;
}
