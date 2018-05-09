package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.model.Customer;
import com.theam.stage2.restapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping(path = "/{customerId}", produces = "application/json")
    public @ResponseBody
    Optional<Customer> getCustomer(@PathVariable int  customerId){
        return customerRepository.findById(customerId);
    }

    @DeleteMapping(path="/delete/{customerId}", headers = "Accept=application/json", consumes = "application/json")
    public @ResponseBody
    void addCustomer(@PathVariable int customerId) {
        customerRepository.deleteById(customerId);
    }

}
