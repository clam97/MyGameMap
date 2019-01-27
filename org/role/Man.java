package role;

public class Man {
    private double health;
    private double damage;
    private double armor;
    private double armorDe;
    private double damageBonus;

    public Man(double health, double damage, double armor, double armorDe, double damageBonus) {
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.armorDe = armorDe;
        this.damageBonus = damageBonus;
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
