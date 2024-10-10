package com.droidsbattle.droids;

import com.droidsbattle.weapons.Weapon;

// Абстрактний клас дроїда (базовий)
public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxDamage;
    protected Weapon weapon;

    // Конструктор класу
    public Droid(String name, int health, int maxDamage) {
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
        this.weapon = null;
    }

    // Метод екіпірування зброї
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println(name + " екіпірував зброю: " + weapon.getName());
    }

    // Отримати загальну шкоду (базову + бонус від зброї)
    public int getTotalDamage() {
        int totalDamage = (int) (Math.random() * (maxDamage + 1)); // Випадкове число в межах від 0 до maxDamage
        if (weapon != null) {
            totalDamage += weapon.getDamageBonus();
        }
        return totalDamage;
    }

    // Отримати ім'я дроїда
    public String getName() {
        return name;
    }

    // Отримати поточне здоров'я дроїда
    public int getHealth() {
        return health;
    }

    // Завдати шкоди дроїду
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    // Метод атаки, що визначається у підкласах
    public abstract void attack(Droid opponent);

    // Перевірка, чи живий дроїд
    public boolean isAlive() {
        return health > 0;
    }
}