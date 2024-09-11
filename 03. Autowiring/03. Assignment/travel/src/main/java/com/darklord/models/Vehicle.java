package com.darklord.models;

import com.darklord.services.VehicleServiceImpl;

public class Vehicle {
    private String name = "Honda";
    private VehicleServiceImpl vehicleServices;


    public Vehicle( VehicleServiceImpl vehicleServices) {
        this.vehicleServices = vehicleServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleServiceImpl getVehicleServices() {
        return vehicleServices;
    }

    public void setVehicleServices(VehicleServiceImpl vehicleServices) {
        this.vehicleServices = vehicleServices;
    }


}
