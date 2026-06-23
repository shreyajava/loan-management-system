package com.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dto.EMIResponse;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.exception.ResourceNotFoundException;
import com.loan.repository.CustomerRepository;
import com.loan.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Loan applyLoan(Long customerId, Loan loan) {

        Customer customer =
                customerRepository
                .findById(customerId)
                .orElse(null);

        loan.setCustomer(customer);

        loan.setStatus("PENDING");

        return loanRepository.save(loan);
    }
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
    
    public Loan approveLoan(Long loanId) {

        Loan loan =
                loanRepository
                .findById(loanId)
                .orElse(null);

        if (loan != null) {

            loan.setStatus("APPROVED");

            return loanRepository.save(loan);
        }

        return null;
    }
    public Loan rejectLoan(Long loanId) {

        Loan loan =
                loanRepository
                .findById(loanId)
                .orElse(null);

        if (loan != null) {

            loan.setStatus("REJECTED");

            return loanRepository.save(loan);
        }

        return null;
    }
    
    public EMIResponse calculateEMI(Long loanId) {

        Loan loan = loanRepository
                .findById(loanId)
                .orElse(null);

        if (loan == null) {
            return null;
        }

        double principal = loan.getLoanAmount();

        double monthlyRate =
                loan.getInterestRate() / (12 * 100);

        int months =
                loan.getTenureMonths();

        double emi =
                (principal * monthlyRate *
                Math.pow(1 + monthlyRate, months))
                /
                (Math.pow(1 + monthlyRate, months) - 1);

        return new EMIResponse(
                loan.getLoanId(),
                loan.getLoanAmount(),
                loan.getInterestRate(),
                loan.getTenureMonths(),
                emi
        );
    }
    public Loan closeLoan(Long loanId) {

        Loan loan = loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan Not Found with ID " + loanId));

        loan.setStatus("CLOSED");

        return loanRepository.save(loan);
    }
    
    
}