package com.bosch.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Floors {
    private List<Floor> floorList;

    public Floors(List<Floor> floorList) {

        this.floorList = floorList;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }
}
