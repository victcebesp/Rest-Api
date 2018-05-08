package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.model.User;
import com.theam.stage2.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add", headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
    public @ResponseBody User addUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public @ResponseBody Iterable<User> getAllCustomers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public @ResponseBody
    Optional<User> getUser(@PathVariable int userId){
        return userRepository.findById(userId);
    }
}