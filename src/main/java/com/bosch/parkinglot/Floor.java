package com.bosch.parkinglot;

public class Floor {
    private int level;
    private VehicleEnum designatedVehicle;

    public Floor(int level, VehicleEnum vehicle) {
        this.designatedVehicle = vehicle;
        this.level = level;
    }

    public VehicleEnum getDesignatedVehicle() {
        return designatedVehicle;
    }

    public int getLevel() {
        return level;
    }
}
