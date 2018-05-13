package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.exceptions.StorageFileNotFoundException;
import com.theam.stage2.restapi.model.Customer;
import com.theam.stage2.restapi.repositories.CustomerRepository;
import com.theam.stage2.restapi.repositories.UserRepository;
import com.theam.stage2.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Optional;

@Secured({"ROLE_ADMIN", "ROLE_USER"})
@Controller
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, UserRepository userRepository, StorageService storageService) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    @PostMapping(headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Customer addCustomer(@RequestBody Customer customer) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int loggedUserId = userRepository.findByUserName(username).get().getUserId();
        customer.setCreatorUser("/localhost:8080/users/" + loggedUserId);
        customer.setLastUpdateUser("/localhost:8080/users/" + loggedUserId);
        customerRepository.save(customer);
        return customer;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(path = "/{customerId}", produces = "application/json")
    public @ResponseBody
    Optional<Customer> getCustomer(@PathVariable int  customerId){
        return customerRepository.findById(customerId);
    }

    @DeleteMapping(path="{customerId}", headers = "Accept=application/json", consumes = "application/json")
    public @ResponseBody
    void addCustomer(@PathVariable int customerId) {
        customerRepository.deleteById(customerId);
    }

    @PutMapping(produces = "application/json")
    public @ResponseBody
    Optional<Customer> updateCustomer(@RequestBody Customer customer){
        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getCustomerId());
        customerToUpdate.get().update(customer, userRepository);
        customerRepository.save(customerToUpdate.get());
        return customerToUpdate;
    }

    @PostMapping("images")
    public String handleFileUpload(@RequestParam("image") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @RequestParam("customerId") Integer customerId) {

        updatePhotoUrl(file.getOriginalFilename(), customerId);
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/customers";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    private void updatePhotoUrl(String filename, int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.get().setPhotoURL("tmp/images/" + filename);
        customerRepository.save(customer.get());
    }

}
