package com.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.loan.dto.EMIResponse;
import com.loan.entity.Loan;
import com.loan.service.LoanService;

@RestController
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/loan/{customerId}")
    public Loan applyLoan(
            @PathVariable Long customerId,
            @RequestBody Loan loan) {

        return service.applyLoan(customerId, loan);
    }
    @GetMapping("/loan")
    public List<Loan> getAllLoans() {
        return service.getAllLoans();
    }
    
    @PutMapping("/loan/approve/{loanId}")
    public Loan approveLoan(
            @PathVariable Long loanId) {

        return service.approveLoan(loanId);
    }
    @PutMapping("/loan/reject/{loanId}")
    public Loan rejectLoan(
            @PathVariable Long loanId) {

        return service.rejectLoan(loanId);
    }
    
    @GetMapping("/loan/emi/{loanId}")
    public EMIResponse calculateEMI(
            @PathVariable Long loanId) {

        return service.calculateEMI(loanId);
    }
    
    @PutMapping("/loan/close/{loanId}")
    public Loan closeLoan(
            @PathVariable Long loanId) {

        return service.closeLoan(loanId);
    }
}