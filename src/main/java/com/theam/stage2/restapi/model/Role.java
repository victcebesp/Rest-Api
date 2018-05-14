package com.theam.stage2.restapi.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        Role role = (Role) o;
        return role.getRoleId() == this.getRoleId() &&
                role.getRole().equals(this.getRole());
    }

    @Override
    public int hashCode(){
        return 37 * role.hashCode() + roleId;
    }
}
