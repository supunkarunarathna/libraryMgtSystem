package com.octcode.library.libraryMgtSystem.service;

import com.octcode.library.libraryMgtSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword()) // Password should be already encoded in the database
                        .roles(user.getRole()) // Assuming `roles` is a List<String>
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
