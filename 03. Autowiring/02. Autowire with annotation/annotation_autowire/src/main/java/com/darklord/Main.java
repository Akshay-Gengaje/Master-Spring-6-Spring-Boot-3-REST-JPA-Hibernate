package com.darklord;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darklord.beans.Person;
import com.darklord.configuration.ProjectConfiguration;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        Person person = context.getBean(Person.class);
        System.out.println("Person Name : " + person.getName());
        System.out.println("Person Vehicle : " + person.getVehicle());

    }
}