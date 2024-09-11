package com.darklord.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darklord.models.Speaker;
import com.darklord.models.Tyre;
import com.darklord.models.VehicleService;

@Component
public class VehicleServiceImpl implements VehicleService {
    private final Speaker speaker;
    private final Tyre tyre;

    @Autowired
    public VehicleServiceImpl(Speaker speaker, Tyre tyres) {
        this.speaker = speaker;
        this.tyre = tyres;
    }

    @Override
    public void makeSound() {
        speaker.makeSound();
    }

    @Override
    public void rotate() {
        tyre.rotate();
    }
}
