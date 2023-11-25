package com.example.HTTD.reponse;

import com.example.HTTD.Entity.Role;
import lombok.*;


import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Set<Role> roles;
}
