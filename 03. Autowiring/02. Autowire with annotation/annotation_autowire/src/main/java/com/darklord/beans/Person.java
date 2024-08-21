package com.darklord.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name = "Lucy";
    @Autowired
    private Vehicle vehicle;

    public Person() {
        System.out.println("Person bean created..");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name);
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", vehicle=" + vehicle + "]";
    }

}
