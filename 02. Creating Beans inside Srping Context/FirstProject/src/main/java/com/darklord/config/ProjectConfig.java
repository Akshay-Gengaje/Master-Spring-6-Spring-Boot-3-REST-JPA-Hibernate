package com.darklord.config;

import com.darklord.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {
    @Primary
    @Bean(name = "audiVehicle")
    Vehicle vehicle1(){
        Vehicle veh = new Vehicle();
        veh.setName("Audi 8");
        return veh;
    }

    @Bean(value = "hondaVehicle")
    Vehicle vehicle2(){
        Vehicle veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }

    @Bean("ferrariVehicle")
    Vehicle vehicle3(){
        Vehicle veh = new Vehicle();
        veh.setName("Ferrari");
        return veh;
    }
    @Bean
    String hello() {
        return "Hello World";
    }
}
