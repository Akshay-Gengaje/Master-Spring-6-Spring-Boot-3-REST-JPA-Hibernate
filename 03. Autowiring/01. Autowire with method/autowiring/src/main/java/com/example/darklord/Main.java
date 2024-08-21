package com.example.darklord;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.darklord.beans.Person;
import com.example.darklord.config.ProjectConfig;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}