package com.octcode.library.libraryMgtSystem.config;

import com.octcode.library.libraryMgtSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public UserDetailsService userDetailsService(){

        return new CustomUserDetailsService();
//        System.out.println("--------------UserDetailsService start-----------------------");
//
//        UserDetails user= User.builder().username("supun@123").password
//                (passwordEncoder().encode("password")).roles("ADMIN").build();
//
//        UserDetails user1=User.builder().username("pooja").password
//                (passwordEncoder().encode("123")).roles("ADMIN").build();
//
//        System.out.println("--------------UserDetailsService end-----------------------");
//
//        return new InMemoryUserDetailsManager(user,user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        System.out.println("--------------PasswordEncoder start-----------------------");

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {

        System.out.println("--------------AuthenticationManager start-----------------------");
        return builder.getAuthenticationManager();
    }
}
