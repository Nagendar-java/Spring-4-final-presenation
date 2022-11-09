package com.digitalbooks.user.entity;

import java.io.Serializable;

public enum UserRoles implements Serializable {
    ROLE_AUTHOR,
    ROLE_READER,
    ROLE_GUEST;

    private UserRoles() {
    }

    public String getStatus() {
        return this.name();
    }
}
