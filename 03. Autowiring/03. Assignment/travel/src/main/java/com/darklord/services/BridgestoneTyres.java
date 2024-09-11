package com.darklord.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.darklord.models.Tyre;

@Component
@Primary
public class BridgestoneTyres implements Tyre {

    @Override
    public void rotate() {
        System.out.println("Rotating with Bridgestone Tyres");
    }

}
