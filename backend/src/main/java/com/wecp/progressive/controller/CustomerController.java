package com.wecp.progressive.controller;


import com.wecp.progressive.entity.Customers;
import com.wecp.progressive.entity.Transactions;
// import com.wecp.progressive.service.CustomerService;
import com.wecp.progressive.service.impl.CustomerServiceImplArraylist;
import com.wecp.progressive.service.impl.CustomerServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServiceImplArraylist customerServiceArraylist;

    @Autowired
    private CustomerServiceImplJpa customerServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() throws SQLException {
        return new ResponseEntity<>(customerServiceImplJpa.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public Customers getCustomerById(@PathVariable int customerId) throws SQLException {
        return customerServiceImplJpa.getCustomerById(customerId);
    }

    @GetMapping("/fromArrayList")
    public List<Customers> getAllCustomersFromArrayList() throws SQLException {
        return customerServiceArraylist.getAllCustomers();
    }

    @GetMapping("/fromArrayList/all")
    public List<Customers> getAllCustomersSortedByNameFromArrayList() throws SQLException {
        return customerServiceArraylist.getAllCustomersSortedByName();
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customers customers) throws SQLException {
        if(customers.getRole() == "USER")
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(customerServiceImplJpa.addCustomer(customers), HttpStatus.CREATED);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addCustomerToArrayList(Customers customers) throws SQLException {
        return new ResponseEntity<>(customerServiceArraylist.addCustomer(customers), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customers customers) throws SQLException {
        customers.setCustomerId(customerId);
        customerServiceImplJpa.updateCustomer(customers);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws SQLException {
        customerServiceImplJpa.deleteCustomer(customerId);
    }

    public ResponseEntity<List<Transactions>> getAllTransactionsByCustomerId(int customerId) {
        return null;
    }
}