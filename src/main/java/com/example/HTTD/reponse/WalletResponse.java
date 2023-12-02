package com.example.HTTD.reponse;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WalletResponse {
    private Long id;
    private String name;
    private Float amount;
    private Date date_created;

    private Long userId;
    private String username;
}
