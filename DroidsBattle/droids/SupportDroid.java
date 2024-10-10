package com.droidsbattle.droids;

import com.droidsbattle.battle.Battle;
import com.droidsbattle.utils.Color;

public class SupportDroid extends Droid {

    private int healAmount; // Поле для зберігання кількості здоров'я, яке може відновити дроїд

    // Конструктор класу SupportDroid
    public SupportDroid(String name) {
        super(name, 80, 10);
        this.healAmount = 15;
    }

    @Override
    public void attack(Droid opponent) {
        int totalDamage = getTotalDamage();
        System.out.println(Color.CYAN + name + Color.RESET + " атакує " + Color.RED + opponent.getName() + Color.RESET + " і завдає " + Color.YELLOW + totalDamage + Color.RESET + " шкоди.");
        opponent.takeDamage(totalDamage);
        Battle.getRecorder().recordTurn(this, opponent, totalDamage);
    }

    // Метод лікування союзника
    public void heal(Droid ally) {
        System.out.println(Color.CYAN + name + Color.RESET + " лікує " + Color.GREEN + ally.getName() + Color.RESET + " на " + Color.GREEN + healAmount + Color.RESET + " одиниць здоров'я.");
        ally.health += healAmount;
        Battle.getRecorder().recordHeal(this, ally, healAmount);
    }
}