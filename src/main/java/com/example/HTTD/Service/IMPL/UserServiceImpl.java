package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.User;
import com.example.HTTD.Repository.UserRepository;
import com.example.HTTD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(Long UserId, User updatedUser) {
        User existingUser = userRepository.findById(UserId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Cập nhật thông tin từ updatedUser
        existingUser.setName(updatedUser.getName());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setBirthday(updatedUser.getBirthday());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setEmail(updatedUser.getEmail());

        // Kiểm tra xem nếu mật khẩu được cung cấp trong yêu cầu thì mới cập nhật
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Lưu lại vào cơ sở dữ liệu
        return userRepository.save(existingUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
