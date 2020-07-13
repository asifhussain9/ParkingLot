package com.bosch.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlotTest {
    @Test
    public void shouldBlockSlot() {
        Slot slot = new Slot(1);
        slot.block();
        assertFalse(slot.isAvailable());
    }

    public void shouldFreeSlot() {
        Slot slot = new Slot(1);
        slot.block();
        slot.free();
        assertTrue(slot.isAvailable());
    }

}