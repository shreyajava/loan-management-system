package com.loan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.entity.Loan;
import com.loan.entity.Payment;
import com.loan.exception.ResourceNotFoundException;
import com.loan.repository.LoanRepository;
import com.loan.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    
    public Payment payEMI(Long loanId, Payment payment) {

        Loan loan = loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan Not Found with ID " + loanId));

        payment.setLoan(loan);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentStatus("PAID");

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByLoan(Long loanId) {
        return paymentRepository.findByLoanLoanId(loanId);
    }
}