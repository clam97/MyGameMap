package org;

public class Monster {
    private int x;
    private int y;
    private int id;
    private double health;
    private double damage;
    private double armor;
    private double armorDe;
    private double damageBonus;

    public Monster(int i, int i1, double v, double v1, double v2, double v3, double v4) {
    }

    public void Monster(int x,int y,int id,double health, double damage, double armor, double armorDe,double damageBonus) {
    }

//    public void Monster(double health,double damage,double armor,double armorDe){
//        this.health=health;
//        this.damage=damage;
//        this.armor=armor;
//        this.armorDe=armorDe;
//
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getArmorDe() {
        return armorDe;
    }

    public void setArmorDe(double armorDe) {
        this.armorDe = armorDe;
    }

    public double getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(double damageBonus) {
        this.damageBonus = damageBonus;
    }
}
