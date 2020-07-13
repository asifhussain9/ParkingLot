package com.bosch.parkinglot;

public class Slot implements Comparable<Slot> {
    private boolean available;
    private int number;

    public boolean isAvailable() {
        return available;
    }

    public int getNumber() {
        return number;
    }

    public Slot(int number) {
        this.available = true;
        this.number = number;
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
}
