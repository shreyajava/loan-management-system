package com.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.loan.entity.Payment;
import com.loan.service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/payment/{loanId}")
    public Payment payEMI(
            @PathVariable Long loanId,
            @RequestBody Payment payment) {

        return service.payEMI(loanId, payment);
    }

    @GetMapping("/payment")
    public List<Payment> getAllPayments() {

        return service.getAllPayments();
    }

    @GetMapping("/payment/loan/{loanId}")
    public List<Payment> getPaymentsByLoan(
            @PathVariable Long loanId) {

        return service.getPaymentsByLoan(loanId);
    }
}