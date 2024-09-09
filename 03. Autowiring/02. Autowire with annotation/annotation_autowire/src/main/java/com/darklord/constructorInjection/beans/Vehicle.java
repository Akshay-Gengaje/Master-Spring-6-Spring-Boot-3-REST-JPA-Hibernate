package com.darklord.constructorInjection.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    String name = "Toyota";

    public Vehicle(){
        super();
    }
    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }
}
