package com.example.HTTD.Service;

import com.example.HTTD.Dto.LoginDTO;
import com.example.HTTD.Dto.UserDTO;
import com.example.HTTD.reponse.LoginResponse;

public interface UserService {

    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
}
