package com.octcode.library.libraryMgtSystem.service;

import com.octcode.library.libraryMgtSystem.dto.UserDto;
import com.octcode.library.libraryMgtSystem.model.User;
import com.octcode.library.libraryMgtSystem.model.UserResponse;
import com.octcode.library.libraryMgtSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public User registerUser(UserDto userDto){

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        User newUser = new User(userDto.getEmail(), userDto.getPassword(), "MEMBER");

        return userRepository.save(newUser);
    }
}
