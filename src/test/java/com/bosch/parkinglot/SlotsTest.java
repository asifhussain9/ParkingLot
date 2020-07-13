package com.bosch.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SlotsTest {

    @Test
    void shouldInitializeSlotsWithList(){
        List<Slot> slotList = new ArrayList<>();
        slotList.add(new Slot(1));
        Slots slots = new Slots(slotList);
        ParkingLot parkingLot = new ParkingLot(slots);
        assertEquals(slots, parkingLot.getSlots());
    }

    @Test
    void getAvailableSlotShouldNotReturnSlotIfNoSlotIsAssigned() {
        Slots slots = new Slots(null);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot();
        assertFalse(allocatedSlots.isPresent());
    }

    @Test
    void getAvailableSlotShouldReturnTheFirstEmptySlot() {
        List<Slot> slotList = new ArrayList<>();
        Slot allocatedSlot1 = new Slot(0);
        Slot allocatedSlot2 = new Slot(1);
        allocatedSlot1.block();
        allocatedSlot2.block();
        Slot freeSlot1 = new Slot(3);
        Slot freeSlot2 = new Slot(2);
        slotList.add(allocatedSlot1);
        slotList.add(allocatedSlot2);
        slotList.add(freeSlot1);
        slotList.add(freeSlot2);

        Slots slots = new Slots(slotList);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot();

        assertTrue(allocatedSlots.isPresent());
        assertEquals(allocatedSlots.get(), freeSlot2);
    }

    @Test
    void getAvailableSlotShouldNotReturnAnySlotIfAllSlotIsBlocked() {
        List<Slot> slotList = new ArrayList<>();
        Slot allocatedSlot1 = new Slot(0);
        Slot allocatedSlot2 = new Slot(1);
        allocatedSlot1.block();
        allocatedSlot2.block();
        slotList.add(allocatedSlot1);
        slotList.add(allocatedSlot2);

        Slots slots = new Slots(slotList);
        Optional<Slot> allocatedSlots = slots.getAvailableSlot();

        assertFalse(allocatedSlots.isPresent());
    }
}