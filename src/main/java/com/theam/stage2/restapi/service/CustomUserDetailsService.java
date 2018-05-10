package com.theam.stage2.restapi.service;

import com.theam.stage2.restapi.model.CustomUserDetails;
import com.theam.stage2.restapi.model.User;
import com.theam.stage2.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return userOptional.map(CustomUserDetails::new).get();
    }
}
