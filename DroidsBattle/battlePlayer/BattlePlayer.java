package com.droidsbattle.battlePlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattlePlayer {

    // Метод для відтворення бою із файлу
    public void playBattleFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Виводимо кожен рядок із файлу на консоль
                try {
                    Thread.sleep(500); // Затримка для читання
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
        }
    }
}