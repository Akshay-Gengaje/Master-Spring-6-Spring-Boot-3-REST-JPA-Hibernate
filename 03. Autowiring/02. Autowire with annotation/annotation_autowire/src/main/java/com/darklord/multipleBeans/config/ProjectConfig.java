package com.darklord.multipleBeans.config;

import com.darklord.multipleBeans.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.darklord.multipleBeans")
public class ProjectConfig {
    @Bean
    Vehicle vehicle1(){
        Vehicle veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }
    @Bean
    Vehicle vehicle2(){
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }
    @Bean
    Vehicle vehicle3(){
        Vehicle veh = new Vehicle();
        veh.setName("Ferrari");
        return veh;
    }
}
