package com.droidsbattle.droids;

import com.droidsbattle.battle.Battle;
import com.droidsbattle.utils.Color;

public class TankDroid extends Droid {

    // Конструктор класу TankDroid
    public TankDroid(String name) {
        super(name, 150, 15);
    }

    @Override
    public void attack(Droid opponent) {
        int totalDamage = getTotalDamage();
        System.out.println(Color.PURPLE + name + Color.RESET + " завдає потужного удару по " + Color.RED + opponent.getName() + Color.RESET + ", завдаючи " + Color.YELLOW + totalDamage + Color.RESET + " шкоди.");
        opponent.takeDamage(totalDamage);
        Battle.getRecorder().recordTurn(this, opponent, totalDamage);
    }
}