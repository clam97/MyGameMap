package org.almShortest;


import role.Man;
import role.Monster;

public class Creature {
    public static Creature creature;

    private Monster[] monsters;
    private Man man;

    private Creature(){}

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public static Creature getCreature(){
        if (creature == null) creature = new Creature();
        return creature;
    }

}
