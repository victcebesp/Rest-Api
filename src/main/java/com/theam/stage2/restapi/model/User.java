package com.theam.stage2.restapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private int active = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(User user, int userId) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.roles = user.getRoles();
        this.id = userId;
    }

    public User(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.active = user.getActive();
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
