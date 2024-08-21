package com.darklord;


import com.darklord.beans.Vehicle;
import com.darklord.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle veh = context.getBean(Vehicle.class);
        veh.setName("Tata");
        System.out.println(veh.getName());
        veh.printHello();
    }
}