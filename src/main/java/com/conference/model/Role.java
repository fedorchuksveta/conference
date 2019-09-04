package com.conference.model;

public enum Role{

    ADMIN("A"),
    Presenter("P"),
    Listener("L");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}