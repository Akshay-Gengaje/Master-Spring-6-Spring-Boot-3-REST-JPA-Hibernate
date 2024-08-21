package com.darklord.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    private String name = "Toyota";

    public Vehicle() {
        System.out.println("Vehicle object is created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle [name=" + name + "]";
    }

}
