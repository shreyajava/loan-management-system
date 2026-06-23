package com.loan.ui;

import org.springframework.beans.factory.annotation.Autowired;
import com.loan.repository.LoanRepository;
import com.loan.repository.PaymentRepository;
import com.loan.service.DashboardService;
import com.loan.service.LoanService;
import com.loan.service.PaymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Payment;
import com.loan.repository.CustomerRepository;

@Controller
public class HomeController {

	@Autowired
	private LoanService loanService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/customers")
    public String customers(Model model) {

        model.addAttribute(
                "customers",
                customerRepository.findAll());

        return "customers";
    }

    @GetMapping("/customer-form")
    public String customerForm(Model model) {

        model.addAttribute(
                "customer",
                new Customer());

        return "customer-form";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(Customer customer) {

        customerRepository.save(customer);

        return "redirect:/customers";
    }

    @GetMapping("/edit-customer/{id}")
    public String editCustomer(
            @PathVariable Long id,
            Model model) {

        Customer customer =
                customerRepository
                        .findById(id)
                        .orElse(null);

        model.addAttribute(
                "customer",
                customer);

        return "customer-form";
    }

    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        customerRepository.deleteById(id);

        return "redirect:/customers";
    }
    
    @Autowired
    private LoanRepository loanRepository;
    @GetMapping("/loans")
    public String loans(Model model) {

        System.out.println("Loans = " + loanRepository.findAll());

        model.addAttribute(
                "loans",
                loanRepository.findAll());

        return "loans";
    }
    
    @GetMapping("/loan-form")
    public String loanForm(Model model) {

        model.addAttribute(
                "loan",
                new Loan());

        return "loan-form";
    }
    
    @PostMapping("/save-loan")
    public String saveLoan(
            Long customerId,
            Loan loan) {

        loanService.applyLoan(customerId, loan);

        return "redirect:/loans";
    }
    
    @GetMapping("/approve-loan/{id}")
    public String approveLoan(
            @PathVariable Long id) {

        loanService.approveLoan(id);

        return "redirect:/loans";
    }
    
    @GetMapping("/reject-loan/{id}")
    public String rejectLoan(
            @PathVariable Long id) {

        loanService.rejectLoan(id);

        return "redirect:/loans";
    }
    
    @GetMapping("/close-loan/{id}")
    public String closeLoan(
            @PathVariable Long id) {

        loanService.closeLoan(id);

        return "redirect:/loans";
    }
    
    @GetMapping("/dashboard-ui")
    public String dashboard(Model model) {

        model.addAttribute(
                "dashboard",
                dashboardService.getDashboardData());

        return "dashboard";
    }
    
    @GetMapping("/emi/{id}")
    public String emi(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "emi",
                loanService.calculateEMI(id));

        return "emi";
    }
    
    @GetMapping("/payments")
    public String payments(Model model) {

        model.addAttribute(
                "payments",
                paymentRepository.findAll());

        return "payments";
    }
    
    @GetMapping("/pay-emi/{id}")
    public String payEMI(
            @PathVariable Long id) {

        Payment payment = new Payment();

        double emiAmount =
                loanService
                .calculateEMI(id)
                .getEmi();

        payment.setAmountPaid(emiAmount);

        paymentService.payEMI(id, payment);

        return "redirect:/payments";
    }
}