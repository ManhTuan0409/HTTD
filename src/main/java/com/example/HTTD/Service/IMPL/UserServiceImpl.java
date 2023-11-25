package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.User;
import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Repository.UserRepository;
import com.example.HTTD.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.get();
    }


}
