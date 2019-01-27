package org.unit;

public class Building {
    private String name;
    private Floor[] floors;

    public Building() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Floor[] getFloors() {
        return this.floors;
    }

    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }
}
