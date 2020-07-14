package com.bosch.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    public void setup() {
        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor(-2, VehicleEnum.BIKE);
        Floor floor2 = new Floor(-1, VehicleEnum.CAR);
        floorList.add(floor1);
        floorList.add(floor2);
        List<Slot> slotList = new LinkedList<>();
        slotList.add(new Slot(1, floor1));
        slotList.add(new Slot(2, floor2));
        this.parkingLot = new ParkingLot(new Slots(slotList), new Floors(floorList));
    }

    @Test
    public void shouldConfirmFloorIsForCarBeforeAssigningSlotToACar() throws Exception {
        Ticket ticket = parkingLot.assignSlot(new Car("Ka 05 s 0195"), VehicleEnum.CAR);
        Optional<Slot> assignedSlot = parkingLot.getSlots().getSlots().stream().filter(x -> x.getNumber() == ticket.getSlotNumber()).findFirst();
        if (assignedSlot.isPresent()) {
            assertEquals(VehicleEnum.CAR, assignedSlot.get().getFloor().getDesignatedVehicle());
        }else{
            throw new Exception("No Slot assigned");
        }
    }

    @Test
    public void shouldAddCarToASlot() throws Exception {
        Ticket ticket = parkingLot.assignSlot(new Car("Ka 05 s 0195"), VehicleEnum.CAR);
        assertEquals(ticket, new Ticket("Ka 05 s 0195", ticket.getEntryTime(), 2));
    }

    @Test
    public void shouldThrowExceptionWhileAddingCarToASlot() throws Exception {
        parkingLot.assignSlot(new Car("Ka 05 s 0195"), VehicleEnum.CAR);
        Assertions.assertThrows(Exception.class, () -> parkingLot.assignSlot(new Car("Ka 05 s 0196"), VehicleEnum.CAR));
    }

    @Test
    public void userShouldPayOnCheckout() throws Exception {
        Car car = new Car("Ka 05 s 0195");
        Ticket ticket = parkingLot.assignSlot(car, VehicleEnum.CAR);
        Instant twoHoursAgo = Instant.now().minusSeconds(2 * 60 * 60);
        ticket.setEntryTime(Date.from(twoHoursAgo));
        long amount = parkingLot.checkout(ticket);
        assertEquals(20, amount);

        Instant thirtyMins = Instant.now().minusSeconds((long) (.5 * 60 * 60));
        ticket.setEntryTime(Date.from(thirtyMins));
        amount = parkingLot.checkout(ticket);
        assertEquals(10, amount);
    }


}