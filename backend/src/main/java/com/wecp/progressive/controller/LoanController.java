package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Loan;
import com.wecp.progressive.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LoanController {
    @Autowired
    private LoanService loanService;
    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }
    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        if(loanService.getLoanById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(loanService.getLoanById(id), HttpStatus.OK);
    }
    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        
        return new ResponseEntity<>(loanService.createLoan(loan), HttpStatus.CREATED);
    }
    @PutMapping("/loans/{id}")
    public ResponseEntity<Void> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        if(loanService.getLoanById(id) != null) 
        loan.setId(id);
        loanService.updateLoan(loan);
        return new ResponseEntity<>(HttpStatus.OK);

        
        
    }
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
