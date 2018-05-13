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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;
        if (roleId != role.roleId) return false;
        return this.role != null ? this.role.equals(role.role) : role.role == null;
    }
}
