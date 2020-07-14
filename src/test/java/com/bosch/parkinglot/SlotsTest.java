package com.bosch.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SlotsTest {

    @Test
    void shouldInitializeSlotsWithList(){
        ArrayList<Floor> floorList = new ArrayList<>();
        List<Slot> slotList = new ArrayList<>();
        slotList.add(new Slot(1, new Floor(0, VehicleEnum.CAR)));
        Slots slots = new Slots(slotList);
        ParkingLot parkingLot = new ParkingLot(slots, new Floors(floorList));
        assertEquals(slots, parkingLot.getSlots());
    }

    @Test
    void getAvailableSlotShouldNotReturnSlotIfNoSlotIsAssigned() {
        Slots slots = new Slots(null);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot(VehicleEnum.CAR);
        assertFalse(allocatedSlots.isPresent());
    }

    @Test
    void getAvailableSlotShouldReturnTheFirstEmptySlot() {
        Floor floor1 = new Floor(-2, VehicleEnum.CAR);
        Floor floor2 = new Floor(-1, VehicleEnum.BIKE);
        ArrayList<Floor> floorList = new ArrayList<>();
        floorList.add(floor1);
        floorList.add(floor2);
        List<Slot> slotList = new ArrayList<>();
        Slot allocatedSlot1 = new Slot(0, floor1);
        Slot allocatedSlot2 = new Slot(1, floor2);
        allocatedSlot1.block();
        allocatedSlot2.block();
        Slot freeSlot1 = new Slot(3, floor1);
        Slot freeSlot2 = new Slot(2, floor2);
        slotList.add(allocatedSlot1);
        slotList.add(allocatedSlot2);
        slotList.add(freeSlot1);
        slotList.add(freeSlot2);

        Slots slots = new Slots(slotList);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot(VehicleEnum.CAR);

        assertTrue(allocatedSlots.isPresent());
        assertEquals(allocatedSlots.get(), freeSlot1);
    }

    @Test
    void getAvailableSlotShouldNotReturnAnySlotIfAllSlotIsBlocked() {
        List<Slot> slotList = new ArrayList<>();
        Slot allocatedSlot1 = new Slot(0, new Floor(-1, VehicleEnum.CAR));
        Slot allocatedSlot2 = new Slot(1, new Floor(0, VehicleEnum.CAR));
        allocatedSlot1.block();
        allocatedSlot2.block();
        slotList.add(allocatedSlot1);
        slotList.add(allocatedSlot2);

        Slots slots = new Slots(slotList);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot(VehicleEnum.CAR);

        assertFalse(allocatedSlots.isPresent());
    }
}