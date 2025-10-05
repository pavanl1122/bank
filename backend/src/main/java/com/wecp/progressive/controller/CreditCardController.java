package com.wecp.progressive.controller;


import com.wecp.progressive.entity.CreditCard;
import com.wecp.progressive.service.CreditCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/credit-cards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;
    @GetMapping("")
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> ls = creditCardService.getAllCreditCards();
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id) {
        if(creditCardService.getCreditCardById(id) != null)
        return new ResponseEntity<>(creditCardService.getCreditCardById(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("")
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        return new ResponseEntity<>(creditCardService.createCreditCard(creditCard), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        creditCard.setId(id);
        creditCardService.updateCreditCard(creditCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        creditCardService.deleteCreditCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

