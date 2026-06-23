package com.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Customer;
import com.loan.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/customer")
    public Customer saveCustomer(
            @Valid @RequestBody Customer customer) {

        return service.saveCustomer(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {

        return service.getAllCustomers();
    }
    
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }
    
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        return service.updateCustomer(id, customer);
    }
    
    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        return service.deleteCustomer(id);
    }
}