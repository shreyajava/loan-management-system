package com.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.entity.Customer;
import com.loan.exception.ResourceNotFoundException;
import com.loan.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    //save
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    //get
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
    
    
    //get by Id
    public Customer getCustomerById(Long id) {

    	return repository
    	        .findById(id)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException(
    	                        "Customer Not Found with ID "
    	                                + id));
    }
    
    //update customer
    public Customer updateCustomer(Long id, Customer customer) {

        Customer dbCustomer =
                repository.findById(id).orElse(null);

        if (dbCustomer != null) {

            dbCustomer.setCustomerName(customer.getCustomerName());
            dbCustomer.setEmail(customer.getEmail());
            dbCustomer.setPhone(customer.getPhone());
            dbCustomer.setAddress(customer.getAddress());

            return repository.save(dbCustomer);
        }

        return null;
    }
    
    //delete
    public String deleteCustomer(Long id) {

        repository.deleteById(id);

        return "Customer Deleted Successfully";
    }
}