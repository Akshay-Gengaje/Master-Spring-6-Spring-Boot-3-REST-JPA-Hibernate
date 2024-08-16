package com.darklord;

import com.darklord.beans.Vehicle;
import com.darklord.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle veh1 = context.getBean("audiVehicle", Vehicle.class);
        System.out.println(veh1.getName());
        Vehicle veh2 = context.getBean("hondaVehicle", Vehicle.class);
        System.out.println(veh2.getName());
        Vehicle veh3 = context.getBean("ferrariVehicle", Vehicle.class);
        System.out.println(veh3.getName());
    }
}