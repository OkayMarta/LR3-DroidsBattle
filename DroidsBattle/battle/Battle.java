package com.droidsbattle.battle;

import com.droidsbattle.droids.Droid;
import com.droidsbattle.battleRecorder.BattleRecorder;
import java.util.List;

public class Battle {
    // Об'єкт для запису бою
    private static BattleRecorder recorder = new BattleRecorder();

    // Метод для бою 1 на 1
    public static void oneOnOne(Droid droid1, Droid droid2) {
        recorder.startRecording("Бій 1 на 1"); // Починаємо запис бою 1 на 1

        System.out.println("Починається бій між " + droid1.getName() + " і " + droid2.getName());
        // Почергові атаки
        while (droid1.isAlive() && droid2.isAlive()) {
            droid1.attack(droid2);
            if (droid2.isAlive()) {
                droid2.attack(droid1);
            }
        }
        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " перемагає!");
            recorder.recordResult(droid1.getName() + " перемагає!");
        } else {
            System.out.println(droid2.getName() + " перемагає!");
            recorder.recordResult(droid2.getName() + " перемагає!");
        }
    }

    // Метод для командного бою
    public static void teamBattle(List<Droid> team1, List<Droid> team2) {
        recorder.startRecording("Командний бій"); // Починаємо запис командного бою

        System.out.println("Починається командний бій!");
        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            Droid attacker = getNextAliveDroid(team1);
            Droid defender = getNextAliveDroid(team2);

            if (attacker != null && defender != null) {
                attacker.attack(defender);
            }

            // Змінюємо команди місцями для наступного ходу
            List<Droid> temp = team1;
            team1 = team2;
            team2 = temp;
        }

        if (teamIsAlive(team1)) {
            System.out.println("Команда 1 перемагає!");
            recorder.recordResult("Команда 1 перемагає!");
        } else {
            System.out.println("Команда 2 перемагає!");
            recorder.recordResult("Команда 2 перемагає!");
        }
    }

    // Метод для перевірки, чи жива команда
    private static boolean teamIsAlive(List<Droid> team) {
        for (Droid d : team) {
            if (d.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // Метод для отримання наступного живого дроїда
    private static Droid getNextAliveDroid(List<Droid> team) {
        for (Droid d : team) {
            if (d.isAlive()) {
                return d;
            }
        }
        return null;
    }

    // Метод для отримання об'єкта для запису бою
    public static BattleRecorder getRecorder() {
        return recorder;
    }
}