package com.wecp.progressive.service;


import com.wecp.progressive.entity.Customers;
import com.wecp.progressive.exception.CustomerAlreadyExistsException;
import com.wecp.progressive.repository.CustomerRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerLoginService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    public CustomerLoginService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customers> getCustomerById(Integer customer) {
        return customerRepository.findById(customer);
    }
    public Customers getCustomerByName(String name)
    {
        return customerRepository.findByUsername(name);
       // return customerRepository.findByUsername()
    }
    //@PreAuthorize("hasRole('ADMIN')")
    // public Customers createCustomer(Customers user) {
    //    user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     return customerRepository.save(user);

    // }
    public Customers createCustomer(Customers user) {
        if(customerRepository.findByEmail(user.getEmail()) != null)
        {
            throw new CustomerAlreadyExistsException("Email already exists");
        }
        if(customerRepository.findByUsername(user.getUsername()) != null)
        {
            throw new CustomerAlreadyExistsException("Username already exists");
            //throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customerRepository.save(user);
    }

    public Customers updateCustomer(Customers customer) {
        return null;
    }

    public void deleteUser(Integer id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
         Customers user = customerRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , new ArrayList<>());
    }

    }
