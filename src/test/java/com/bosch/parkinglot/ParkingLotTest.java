package com.bosch.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void userShouldPayOnCheckout() throws Exception {
        Car car = new Car("Ka 05 s 0195");
        Ticket ticket = parkingLot.assignSlot(car);
        Instant twoHoursAgo = Instant.now().minusSeconds(2*60*60);
        ticket.setEntryTime(Date.from(twoHoursAgo));
        long amount = parkingLot.checkout(ticket);
        assertEquals(20,amount);

        Instant thirtyMins = Instant.now().minusSeconds((long) (.5*60*60));
        ticket.setEntryTime(Date.from(thirtyMins));
        amount = parkingLot.checkout(ticket);
        assertEquals(10,amount);
    }


}