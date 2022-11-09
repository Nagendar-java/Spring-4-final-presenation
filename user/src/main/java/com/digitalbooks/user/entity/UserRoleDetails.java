package com.digitalbooks.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRoleDetails {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @JsonProperty("userroles")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRoles getRole() {
        return this.role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public UserRoleDetails(Integer id, UserRoles role) {
        this.id = id;
        this.role = role;
    }

    public UserRoleDetails() {
    }

    public String toString() {
        return "UserRoleDetails [id=" + this.id + ", role=" + this.role + "]";
    }
}
