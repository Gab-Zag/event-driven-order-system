package com.gab.event_driven_order_system.user.entity.user;

public enum UserType {
    CLIENT("client");

    private String role;

    UserType(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
