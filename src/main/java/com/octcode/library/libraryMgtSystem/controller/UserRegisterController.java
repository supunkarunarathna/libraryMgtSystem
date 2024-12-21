package com.octcode.library.libraryMgtSystem.controller;

import com.octcode.library.libraryMgtSystem.dto.UserDto;
import com.octcode.library.libraryMgtSystem.model.UserResponse;
import com.octcode.library.libraryMgtSystem.service.UserSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/signup")
public class UserRegisterController {

    @Autowired
    private UserSignupService userSignupService;

    @PostMapping("/registeruser")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userdto) throws Exception{

        System.out.println("--------------registerUser start-----------------------");
        try {
            userSignupService.registerUser(userdto);
            return ResponseEntity.ok("User registered successfully!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
