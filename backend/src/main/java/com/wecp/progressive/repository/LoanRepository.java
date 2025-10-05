package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
}