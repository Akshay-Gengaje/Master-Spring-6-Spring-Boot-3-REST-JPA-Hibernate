package com.darklord.config;

import com.darklord.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    Vehicle vehicle1(){
        Vehicle veh = new Vehicle();
        veh.setName("Audi 8");
        return veh;
    }

    @Bean
    Vehicle vehicle2(){
        Vehicle veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }

    @Bean
    Vehicle vehicle3(){
        Vehicle veh = new Vehicle();
        veh.setName("Lambo");
        return veh;
    }
    @Bean
    String hello() {
        return "Hello World";
    }
}
