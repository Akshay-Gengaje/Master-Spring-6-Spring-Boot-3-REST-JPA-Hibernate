package com.example.darklord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.darklord.beans.Person;
import com.example.darklord.beans.Vehicle;

@Configuration
public class ProjectConfig {
    @Bean
    public Vehicle vehicle() {
        Vehicle veh = new Vehicle();
        veh.setName("Tata");
        return veh;
    }
    // Bean created by calling vehicle() method
    // @Bean
    // public Person person() {
    // Person person = new Person();
    // person.setName("John Doe");
    // person.setVehicle(vehicle());
    // return person;
    // }

    // Bean created by passing vehicle parameter
    @Bean
    public Person person(Vehicle vehicle) {
        Person person = new Person();
        person.setName("John");
        person.setVehicle(vehicle);
        return person;
    }
}
