package com.wecp.progressive.service;

import com.wecp.progressive.entity.Loan;
import com.wecp.progressive.repository.LoanRepository;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        if(loanRepository.findById(id).isPresent())
        return loanRepository.findById(id).get();
        return null;
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public void updateLoan(Loan loan) {
        if(loanRepository.existsById(loan.getId())){
        Loan l = loanRepository.findById(loan.getId()).get();
        l.setAmount(loan.getAmount());
        l.setDuration(loan.getDuration());
        l.setLoanType(loan.getLoanType());
        loanRepository.save(l);
        }

    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);

    }
}