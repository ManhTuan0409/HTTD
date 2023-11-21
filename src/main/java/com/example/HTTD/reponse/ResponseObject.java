package com.example.HTTD.reponse;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {
    String message;
    Boolean status;
}
