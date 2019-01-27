package org.unit;

public class Location {
    private String name;
    private Building[] buildings;

    public Location() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Building[] getBuildings() {
        return this.buildings;
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }
}
