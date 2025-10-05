package com.wecp.progressive.controller;


import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.entity.Customers;
import com.wecp.progressive.jwt.JwtUtil;
import com.wecp.progressive.service.CustomerLoginService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
@Controller
public class CustomerLoginController {
    private final CustomerLoginService customerLoginService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    

    public CustomerLoginController(CustomerLoginService customerLoginService,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.customerLoginService = customerLoginService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    public ResponseEntity<Customers> registerUser(@RequestBody Customers user) {
        return ResponseEntity.ok(customerLoginService.createCustomer(user));
    }

    public ResponseEntity loginUser(@RequestBody LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        }catch(AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "Invalid username or password", e);
        }

        final UserDetails userDetails = customerLoginService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));

    }
}