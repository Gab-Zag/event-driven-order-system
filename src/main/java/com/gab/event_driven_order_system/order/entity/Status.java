package com.gab.event_driven_order_system.order.entity;

public enum Status {
    PENDING("pending"),
    FINISHED("finished"),
    CANCELED("canceled");

    private String role;

    Status(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
