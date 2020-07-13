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

    public void freeSlot(int slotNumber) throws Exception {
        Optional<Slot> bookedSlot = slots.stream().filter(x -> x.getNumber() == slotNumber).findFirst();
        if (bookedSlot.isPresent()) {
            bookedSlot.get().free();
            return;
        }
        throw new Exception("slot to be free could not be found");
    }
}
