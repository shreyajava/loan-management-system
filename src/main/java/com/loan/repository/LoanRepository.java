package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.entity.Loan;

public interface LoanRepository
extends JpaRepository<Loan, Long> {

long countByStatus(String status);
}