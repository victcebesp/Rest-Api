package com.theam.stage2.restapi.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    public User() {
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.active = user.getActive();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "{\"userName\": \"" + userName + "\" ," +
                "\"active\": \"" + active + "\" ," +
                "\"id\": \"" + id + "\"}";
    }

}