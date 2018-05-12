package com.theam.stage2.restapi.model;

import com.theam.stage2.restapi.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "photo_url")
    private String photoURL = "";

    private String creatorUser;

    private String lastUpdateUser;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public void update(Customer customer, UserRepository userRepository) {
        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (customer.getName() != null) this.name = customer.getName();
        if (customer.getSurname() != null) this.surname = customer.getSurname();
        if (customer.getPhotoURL() != null) this.photoURL = customer.getPhotoURL();
        int loggedUserId = userRepository.findByUserName(loggedUsername).get().getId();
        this.setLastUpdateUser("localhost:8080/users/" + loggedUserId);
    }
}
