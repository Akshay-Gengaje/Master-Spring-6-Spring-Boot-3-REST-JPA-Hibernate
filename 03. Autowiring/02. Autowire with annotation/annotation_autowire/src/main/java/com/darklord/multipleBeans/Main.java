package com.darklord.multipleBeans;

import com.darklord.multipleBeans.beans.Person;
import com.darklord.multipleBeans.beans.Vehicle;
import com.darklord.multipleBeans.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean("person", Person.class);
//        Vehicle vehicle = context.getBean("vehicle1", Vehicle.class);
        System.out.println("Person name from Spring context is : "+ person.getName());
        System.out.println("Vehicle name that person owns : "+ person.getVehicle());
    }
}
