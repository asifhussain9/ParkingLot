package com.bosch.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    public void setup() {
        List<Slot> slotList = new ArrayList<>();
        slotList.add(new Slot(1));
        this.parkingLot = new ParkingLot(new Slots(slotList));
    }

    @Test
    public void shouldAddCarToASlot() throws Exception {
        Ticket ticket = parkingLot.assignSlot(new Car("Ka 05 s 0195"));
        assertEquals(ticket, new Ticket("Ka 05 s 0195", ticket.getEntryTime(), 1));
    }

    @Test
    public void shouldThrowExceptionWhileAddingCarToASlot() throws Exception {
        parkingLot.assignSlot(new Car("Ka 05 s 0195"));
        Assertions.assertThrows(Exception.class, () -> parkingLot.assignSlot(new Car("Ka 05 s 0196")));
    }


}