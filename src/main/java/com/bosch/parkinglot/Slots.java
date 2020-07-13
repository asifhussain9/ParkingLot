package com.bosch.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Slots {
    private List<Slot> slots;

    public Slots(List<Slot> slots) {
        if (slots == null) {
            this.slots = new ArrayList<>();
            return;
        }
        this.slots = slots;
    }

    public Optional<Slot> getAvailableSlot() {
        Collections.sort(slots);
        return slots.stream().filter(Slot::isAvailable).findFirst();
    }
}
