package com.wecp.progressive.service.impl;

import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Customers;
import com.wecp.progressive.exception.CustomerAlreadyExistsException;
import com.wecp.progressive.repository.AccountRepository;
import com.wecp.progressive.repository.CustomerRepository;
import com.wecp.progressive.repository.TransactionRepository;
import com.wecp.progressive.service.CustomerService;

@Service
public class CustomerServiceImplJpa implements CustomerService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    public CustomerServiceImplJpa(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customerRepository.findAll();
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @Override
    public int addCustomer(Customers customers) throws SQLException {
        if(customerRepository.findByEmail(customers.getEmail()) != null || customerRepository.findByUsername(customers.getUsername()) != null)
        {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }
        customers.setPassword(passwordEncoder.encode(customers.getPassword()));
        return customerRepository.save(customers).getCustomerId();
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> sortedCustomers = getAllCustomers();
        Collections.sort(sortedCustomers);
        return sortedCustomers;
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        if(customers.getRole()==null || customers.getRole().isEmpty())
        {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }
        customers.setPassword(passwordEncoder.encode(customers.getPassword()));
        customerRepository.save(customers);
    }
    
    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerRepository.deleteById(customerId);
        accountRepository.deleteByCustomerId(customerId);
        //transactionRepository.deleteByCustomerId(customerId);
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        Optional<Customers> customer = customerRepository.findById(customerId);
        return ((customer.isEmpty())?null:customer.get());
    }
    
}