package com.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dto.DashboardResponse;
import com.loan.repository.CustomerRepository;
import com.loan.repository.LoanRepository;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    public DashboardResponse getDashboardData() {

        long totalCustomers =
                customerRepository.count();

        long totalLoans =
                loanRepository.count();

        long approvedLoans =
                loanRepository.countByStatus("APPROVED");

        long rejectedLoans =
                loanRepository.countByStatus("REJECTED");

        long pendingLoans =
                loanRepository.countByStatus("PENDING");

        long closedLoans =
                loanRepository.countByStatus("CLOSED");

        return new DashboardResponse(
                totalCustomers,
                totalLoans,
                approvedLoans,
                rejectedLoans,
                pendingLoans,
                closedLoans
        );
    }
}