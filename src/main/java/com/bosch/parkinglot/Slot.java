package com.bosch.parkinglot;

public class Slot implements Comparable<Slot> {
    private boolean available;
    private int number;
    private Floor floor;

    public boolean isAvailable() {
        return available;
    }

    public int getNumber() {
        return number;
    }

    public Slot(int number, Floor floor) {
        this.available = true;
        this.number = number;
        this.floor = floor;
    }

    public void block() {
        this.available = false;
    }

    public void free() {
        this.available = true;
    }

    @Override
    public int compareTo(Slot o) {
        return Integer.compare(this.getNumber(), o.getNumber());
    }

    public Floor getFloor() {
        return floor;
    }
}
