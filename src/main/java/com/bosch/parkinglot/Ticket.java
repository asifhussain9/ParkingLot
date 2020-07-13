package com.bosch.parkinglot;

import java.util.Date;

public class Ticket {
    private String carNumber;
    private Date entryTime;
    private int slotNumber;

    public Ticket(String carNumber, Date entryTime, int slotNumber) {
        this.carNumber = carNumber;
        this.entryTime = entryTime;
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (slotNumber != ticket.slotNumber) return false;
        if (carNumber != null ? !carNumber.equals(ticket.carNumber) : ticket.carNumber != null) return false;
        return entryTime != null ? entryTime.equals(ticket.entryTime) : ticket.entryTime == null;
    }

    @Override
    public int hashCode() {
        int result = carNumber != null ? carNumber.hashCode() : 0;
        result = 31 * result + (entryTime != null ? entryTime.hashCode() : 0);
        result = 31 * result + slotNumber;
        return result;
    }

    public Date getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}
