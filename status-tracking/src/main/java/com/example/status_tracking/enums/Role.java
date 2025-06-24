package com.example.status_tracking.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    User, Admin, Manager;

    @JsonCreator
    public static Role fromString(String role) {
        return Role.valueOf(role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase());
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}
