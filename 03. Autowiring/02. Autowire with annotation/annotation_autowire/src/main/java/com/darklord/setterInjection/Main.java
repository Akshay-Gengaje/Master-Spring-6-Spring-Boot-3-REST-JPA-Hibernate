package com.darklord.setterInjection;

import com.darklord.setterInjection.beans.Person;
import com.darklord.setterInjection.beans.Vehicle;
import com.darklord.setterInjection.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean("person", Person.class);
        Vehicle vehicle = context.getBean("vehicle", Vehicle.class);
        System.out.println("Person name from Spring context is : "+ person.getName());
        System.out.println("Vehicle name from Spring context is : "+ vehicle.getName());
        System.out.println("Vehicle name that person owns : "+ person.getVehicle());


    }
}
