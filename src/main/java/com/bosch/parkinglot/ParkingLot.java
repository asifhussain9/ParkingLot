package com.bosch.parkinglot;

import java.util.Date;
import java.util.Optional;

public class ParkingLot {
    private Slots slots;

    public ParkingLot(Slots slots) {
        this.slots = slots;
    }

    public Ticket assignSlot(Car car) throws Exception {
        Optional<Slot> slot = slots.getAvailableSlot();
        if(slot.isPresent()){
            slot.get().block();
            return new Ticket(car.getNumber(), new Date(), slot.get().getNumber());
        }
        throw new Exception("Unable to assign slot");
    }
}
