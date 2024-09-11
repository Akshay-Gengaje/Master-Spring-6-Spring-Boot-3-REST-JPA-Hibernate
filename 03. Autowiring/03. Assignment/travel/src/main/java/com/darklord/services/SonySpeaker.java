package com.darklord.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.darklord.models.Speaker;

@Component
@Primary
public class SonySpeaker implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("Playing music with Sony Speaker");
    }

}
