package com.darklord;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darklord.config.Config;
import com.darklord.models.Person;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person person = context.getBean("personBean", Person.class);
    }
}