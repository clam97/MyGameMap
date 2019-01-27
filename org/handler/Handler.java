package handler;


import role.Man;
import role.Monster;

public class Handler {
    public static boolean isFight(Man man, Monster monster){
        return manForce(man,monster) > monsterFroce(monster,man);
    }

    public static double manForce(Man man, Monster monster){
        double manAliveTime;
        double manLeepAtio;

        double monsterDamage = monster.getDamage();
        double monsterDamBo = monster.getDamageBonus();
        double monsterArmorDe = monster.getArmorDe();

        double manArmor = man.getArmor();

        manLeepAtio = -((1-manArmor/100+monsterArmorDe/100)*monsterDamage*(1+monsterDamBo/100));
        manAliveTime = (-man.getHealth())/(manLeepAtio);

        return manAliveTime;

    }

    public static double monsterFroce(Monster monster , Man man){
        double monsterAliveTime;
        double monsterLeepAtio;

        double manDamage = man.getDamage();
        double manDambo = man.getDamageBonus();
        double manArmorDe = man.getArmorDe();

        double monsterArmor = monster.getArmor();

        monsterLeepAtio = -((1-monsterArmor/100+manArmorDe/100)*manDamage*(1+manDambo/100));
        monsterAliveTime = (-monster.getHealth())/(monsterLeepAtio);

        return monsterAliveTime;
    }

    public static void reduce(Man man, Monster monster){
        man.setHealth((-((1-man.getArmor()/100+monster.getArmorDe()/100)*monster.getDamage()*(1+monster.getDamageBonus()/100)))*monsterFroce(monster,man)+man.getHealth());
    }
}
