package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.model.User;
import com.theam.stage2.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Secured("ROLE_ADMIN")
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

    @DeleteMapping(path = "/delete/{userId}")
    public @ResponseBody
    void deleteUser(@PathVariable int userId){
        userRepository.deleteById(userId);
    }

    @PutMapping(path = "/update/{userId}", produces = "application/json")
    public @ResponseBody
    User updateUser(@RequestBody User user, @PathVariable int userId){
        //TODO: get current values of the user in case some of them are empty
        User userToUpdate = new User(user, userId);
        userRepository.save(userToUpdate);
        return userToUpdate;
    }
}