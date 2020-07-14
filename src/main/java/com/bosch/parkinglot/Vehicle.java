package com.bosch.parkinglot;

import java.util.Objects;

public class Vehicle {
    protected String number;

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(number, vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
