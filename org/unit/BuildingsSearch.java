package org.unit;


public class BuildingsSearch {
    public BuildingsSearch() {
    }

    public String funcSearch(String func, Location location) {
        String local = "";
        int isFound = 0;
        Building[] buildings = location.getBuildings();

        for (Building building:buildings){
            System.out.println(building.getName());
            for (Floor floor:building.getFloors()){
                System.out.println(floor.getFloorNum());
                if (floor.getName().equals(func)) return building.getName();

                if(floor.getRooms() != null){
                    for (Room room:floor.getRooms()){
                        if (room.getFunction().equals(func)) return building.getName();

                    }
                }
            }
        }

        if (isFound == 0) {
            local = "Not Found !!!";
        }

        return local;
    }
}