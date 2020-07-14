package com.bosch.parkinglot;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingLot {
    private Slots slots;
    private Floors floors;

    public ParkingLot(Slots slots, Floors floors) {
        this.slots = slots;
        this.floors = floors;
    }

    public Slots getSlots() {
        return slots;
    }

    public Ticket assignSlot(Vehicle vehicle, VehicleEnum vehicleType) throws Exception {
        Optional<Slot> slot = slots.getAvailableSlot(vehicleType);
        if (slot.isPresent()) {
            slot.get().block();
            return new Ticket(vehicle.getNumber(), new Date(), slot.get().getNumber());
        }
        throw new Exception("Unable to assign slot");
    }

    public long checkout(Ticket ticket) throws Exception {
        long hoursPassed = (System.currentTimeMillis() - ticket.getEntryTime().getTime()) / (1000*60*60);
        slots.freeSlot(ticket.getSlotNumber());
        if (hoursPassed == 0){
            return 10L;
        }
        return hoursPassed*10;
    }

    public Floors getFloors() {
        return floors;
    }
}
