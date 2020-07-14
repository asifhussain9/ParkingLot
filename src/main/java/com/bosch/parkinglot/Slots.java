package com.bosch.parkinglot;

import java.util.*;
import java.util.stream.Collectors;

public class Slots {
    private List<Slot> slots;

    public Slots(List<Slot> slots) {
        if (slots == null) {
            this.slots = new ArrayList<>();
            return;
        }
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public Optional<Slot> getAvailableSlot(VehicleEnum vehicle) {
        List<Slot> slotList = slots.stream().filter(x -> x.getFloor().getDesignatedVehicle().equals(vehicle)).collect(Collectors.toList());
        return slotList.stream().filter(Slot::isAvailable).min(Comparator.comparing(Slot::getNumber));
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
