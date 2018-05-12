package com.theam.stage2.restapi.controller;

import com.theam.stage2.restapi.model.Role;
import com.theam.stage2.restapi.model.User;
import com.theam.stage2.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
    public @ResponseBody User addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder(11).encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public @ResponseBody
    Optional<User> getUser(@PathVariable int userId){
        return userRepository.findById(userId);
    }

    @DeleteMapping(path = "/{userId}")
    public @ResponseBody
    void deleteUser(@PathVariable int userId){
        userRepository.deleteById(userId);
    }

    @PutMapping(produces = "application/json")
    public @ResponseBody
    Optional<User> updateUser(@RequestBody User user){
        Optional<User> userToUpdate = userRepository.findById(user.getUserId());
        userToUpdate.get().update(user);
        userRepository.save(userToUpdate.get());
        return userToUpdate;
    }

    @PostMapping(path = "/{userId}/roles", produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
    public @ResponseBody
    Optional<User> addUserRol(@RequestBody Role role, @PathVariable int userId){
        Optional<User> user = userRepository.findById(userId);
        Set<Role> roles = user.get().getRoles();
        roles.add(role);
        user.get().setRoles(roles);
        return user;
    }

    @DeleteMapping(path = "/{userId}/roles", produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
    public @ResponseBody
    Optional<User> removeUserRol(@RequestBody Role role, @PathVariable int userId){
        Optional<User> user = userRepository.findById(userId);
        Set<Role> roles = user.get().getRoles();
        if (roles.contains(role)) roles.remove(role);
        return user;
    }
}