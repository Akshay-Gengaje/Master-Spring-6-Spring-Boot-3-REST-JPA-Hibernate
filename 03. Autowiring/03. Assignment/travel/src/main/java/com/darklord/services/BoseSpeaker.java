package com.darklord.services;

import com.darklord.models.Speaker;

public class BoseSpeaker implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("Playing music with Bose Speaker");
    }

}
