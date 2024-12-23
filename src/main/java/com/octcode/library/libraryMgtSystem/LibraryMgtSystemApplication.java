package com.octcode.library.libraryMgtSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibraryMgtSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryMgtSystemApplication.class, args);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "password";  // Replace with your password
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println("Encoded Password: " + encodedPassword);
	}

}
