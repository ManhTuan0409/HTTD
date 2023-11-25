package com.example.HTTD.Controller;

import com.example.HTTD.Dto.LoginDto;
import com.example.HTTD.Dto.SignUpDto;
import com.example.HTTD.Entity.Role;
import com.example.HTTD.Entity.User;
import com.example.HTTD.Repository.RoleRepository;
import com.example.HTTD.Repository.UserRepository;
import com.example.HTTD.Service.IMPL.CustomUserDetailService;
import com.example.HTTD.Service.UserService;
import com.example.HTTD.reponse.ResponseObject;
import com.example.HTTD.reponse.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            // Retrieve user details from the database using the repository
            Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserResponse userResponse = new UserResponse();
                userResponse.setId(user.getId());
                userResponse.setName(user.getName());
                userResponse.setUsername(user.getUsername());
                userResponse.setEmail(user.getEmail());
                userResponse.setRoles(user.getRoles());
                // Process user details as needed
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Đăng nhập thành công", true, userResponse)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject(0, "Đăng nhập thất bại", false, "")
                );
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra", false, "")
            );
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody SignUpDto signUpDto){
        try{
            // add check for username exists in a DB
            if(userRepository.existsByUsername(signUpDto.getUsername())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject(0, "Tài khoản đã tồn tại",false, "")
                );
            }

            // add check for email exists in DB
            if(userRepository.existsByEmail(signUpDto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject(0, "Email đã tồn tại",false, "")
                );
            }

            // create user object
            User user = new User();
            user.setName(signUpDto.getName());
            user.setUsername(signUpDto.getUsername());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            Role roles = roleRepository.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singleton(roles));

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Đăng ký tài khoản thành công",true, "")
            );
        }catch (AuthenticationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra", false, "")
            );
        }

    }
}
