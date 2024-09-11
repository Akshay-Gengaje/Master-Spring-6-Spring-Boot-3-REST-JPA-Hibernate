package com.darklord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.darklord.services.BoseSpeaker;
import com.darklord.services.MichelinTyres;

@Configuration
@ComponentScan(basePackages = "com.darklord")
public class Config {
    @Bean
    public BoseSpeaker speaker() {
        return new BoseSpeaker();
    }

    @Bean
    public MichelinTyres tyre() {
        return new MichelinTyres();
    }
}
