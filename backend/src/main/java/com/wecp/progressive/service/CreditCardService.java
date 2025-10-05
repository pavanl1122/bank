package com.wecp.progressive.service;


import com.wecp.progressive.entity.CreditCard;
import com.wecp.progressive.repository.CreditCardRepository;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    public CreditCard getCreditCardById(Long id) {
        if(creditCardRepository.existsById(id))
        return creditCardRepository.findById(id).get();
        return null;
    }

    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void updateCreditCard(CreditCard creditCard) {
        CreditCard cc = creditCardRepository.findById(creditCard.getId()).get();
        cc.setCardNumber(creditCard.getCardNumber());
        cc.setCardHolderName(creditCard.getCardHolderName());
        creditCardRepository.save(cc);
    }

    public void deleteCreditCard(Long id) {
        creditCardRepository.deleteById(id);
    }
}

