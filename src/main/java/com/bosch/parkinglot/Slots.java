package com.bosch.parkinglot;

import java.util.List;
import java.util.Optional;

public class Slots {
    private List<Slot> slots;

    public Slots(List<Slot> slots) {
        this.slots = slots;
    }

    public Optional<Slot> getAvailableSlot() {
        return slots.stream().filter(Slot::isAvailable).findFirst();
    }
}
