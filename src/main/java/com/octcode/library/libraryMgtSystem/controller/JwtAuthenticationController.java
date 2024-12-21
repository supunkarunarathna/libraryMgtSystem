package com.octcode.library.libraryMgtSystem.controller;

import com.octcode.library.libraryMgtSystem.model.JwtRequest;
import com.octcode.library.libraryMgtSystem.model.JwtResponse;
import com.octcode.library.libraryMgtSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class JwtAuthenticationController {


    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println("--------------createAuthenticationToken start-----------------------");

        try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .message("Successfully Logged In").build();

            System.out.println("--------------createAuthenticationToken end-----------------------");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            JwtResponse response = JwtResponse.builder()
                    .jwtToken(null)
                    .message(e.getMessage()).build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {

            System.out.println("--------------authenticate start-----------------------");

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
            manager.authenticate(authentication);

            System.out.println("--------------authenticate end-----------------------");

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
