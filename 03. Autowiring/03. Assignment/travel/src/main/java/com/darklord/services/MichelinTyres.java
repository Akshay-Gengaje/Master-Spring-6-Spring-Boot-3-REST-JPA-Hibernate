package com.darklord.services;

import com.darklord.models.Tyre;

public class MichelinTyres implements Tyre {

    @Override
    public void rotate() {
        System.out.println("Rotating with Michelin Tyres");
    }

}
