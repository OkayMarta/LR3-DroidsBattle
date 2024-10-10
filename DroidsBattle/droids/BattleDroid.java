package com.droidsbattle.droids;

import com.droidsbattle.battle.Battle;
import com.droidsbattle.utils.Color;

public class BattleDroid extends Droid {

    // Конструктор класу BattleDroid
    public BattleDroid(String name) {
        super(name, 100, 20);
    }

    @Override
    public void attack(Droid opponent) {
        int totalDamage = getTotalDamage();
        System.out.println(Color.BLUE + name + Color.RESET + " атакує " + Color.RED + opponent.getName() + Color.RESET + " і завдає " + Color.YELLOW + totalDamage + Color.RESET + " шкоди.");
        opponent.takeDamage(totalDamage);
        Battle.getRecorder().recordTurn(this, opponent, totalDamage);
    }
}