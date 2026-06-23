package com.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.entity.Payment;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    List<Payment> findByLoanLoanId(Long loanId);

}