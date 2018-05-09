package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.model.Customer;
import com.theam.stage2.restapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path="/add", headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Customer addCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @GetMapping(path="/all", produces = "application/json")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }



}
