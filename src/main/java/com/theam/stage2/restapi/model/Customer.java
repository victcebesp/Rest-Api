package com.theam.stage2.restapi.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

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

    public Customer cloneCustomer(int customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPhotoURL(photoURL);
        customer.setCreatorUser(creatorUser);
        customer.setLastUpdateUser(lastUpdateUser);
        return customer;
    }

}
