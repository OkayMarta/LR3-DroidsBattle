package com.droidsbattle.battleRecorder;

import com.droidsbattle.droids.Droid;

import java.io.FileWriter;
import java.io.IOException;

public class BattleRecorder {
    private StringBuilder battleLog;

    // Конструктор класу
    public BattleRecorder() {
        battleLog = new StringBuilder();
    }

    // Метод для початку запису бою
    public void startRecording(String battleType) {
        battleLog.append(battleType).append(":\n");
    }

    // Метод для запису ходу бою
    public void recordTurn(Droid attacker, Droid defender, int damage) {
        battleLog.append(attacker.getClass().getSimpleName())
                .append(" ")
                .append(attacker.getName())
                .append(" атакує ")
                .append(defender.getClass().getSimpleName())
                .append(" ")
                .append(defender.getName())
                .append(" і наносить ")
                .append(damage)
                .append(" шкоди. Здоров'я ")
                .append(defender.getName())
                .append(": ")
                .append(defender.getHealth())
                .append(".\n");
    }

    // Метод для запису лікування
    public void recordHeal(Droid healer, Droid target, int healAmount) {
        battleLog.append(healer.getClass().getSimpleName())
                .append(" ")
                .append(healer.getName())
                .append(" лікує ")
                .append(target.getClass().getSimpleName())
                .append(" ")
                .append(target.getName())
                .append(" на ")
                .append(healAmount)
                .append(" одиниць здоров'я. Здоров'я ")
                .append(target.getName())
                .append(": ")
                .append(target.getHealth())
                .append(".\n");
    }

    // Метод для запису результату бою
    public void recordResult(String result) {
        battleLog.append(result).append("\n---\n");
    }

    // Метод для збереження бою в файл
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) { // true - для дозапису у файл
            writer.write(battleLog.toString());
        } catch (IOException e) {
            System.err.println("Помилка під час запису бою у файл: " + e.getMessage());
        }
    }
}