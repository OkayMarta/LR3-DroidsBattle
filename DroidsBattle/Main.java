package com.droidsbattle;

import com.droidsbattle.droids.*;
import com.droidsbattle.weapons.*;
import com.droidsbattle.battle.Battle;
import com.droidsbattle.battlePlayer.BattlePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Droid> droidList = new ArrayList<>(); // список для збереження всіх дроїдів
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneOnOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    System.out.print("Введіть ім'я файлу для запису бою (з розширенням .txt): ");
                    String filename = scanner.next();
                    Battle.getRecorder().saveToFile(filename); // Запис бою у файл
                    System.out.println("Бій записаний у файл " + filename);
                    break;
                case 6:
                    System.out.print("Введіть ім'я файлу для відтворення бою: ");
                    String nameOfFile = scanner.next();
                    BattlePlayer player = new BattlePlayer();
                    player.playBattleFromFile(nameOfFile); // Відтворення бою із файлу
                    break;
                case 7:
                    exit = true;
                    System.out.println("Вихід із програми.");
                    break;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    // Метод виведення меню
    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити бій команда на команду");
        System.out.println("5. Записати проведений бій у файл");
        System.out.println("6. Відтворити проведений бій із файлу");
        System.out.println("7. Вийти з програми");
        System.out.print("Виберіть дію: ");
    }

    // Метод отримання вибору користувача
    private static int getUserChoice() {
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            scanner.next(); // Очистити неправильне введення
        }
        return choice;
    }

    // Метод створення дроїду
    private static void createDroid() {
        System.out.println("\nВиберіть тип дроїда:");
        System.out.println("1. BattleDroid");
        System.out.println("2. SupportDroid");
        System.out.println("3. TankDroid");
        System.out.print("Введіть номер дроїду: ");
        int type = getUserChoice();
        scanner.nextLine(); // Очистити перехід на новий рядок

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        Droid newDroid = null;
        switch (type) {
            case 1:
                newDroid = new BattleDroid(name);
                break;
            case 2:
                newDroid = new SupportDroid(name);
                break;
            case 3:
                newDroid = new TankDroid(name);
                break;
            default:
                System.out.println("Неправильний вибір типу дроїду.");
                return;
        }

        // Можливість екіпірувати зброю
        System.out.print("Хочете екіпірувати дроїда зброєю? (так/ні): ");
        String equipChoice = scanner.nextLine();
        if (equipChoice.equalsIgnoreCase("так")) {
            equipWeapon(newDroid);
        }

        droidList.add(newDroid);
        System.out.println("Дроїд " + newDroid.getName() + " створений та доданий до списку.");
    }

    // Метод екіпірування зброї
    private static void equipWeapon(Droid droid) {
        System.out.println("Виберіть зброю: ");
        System.out.println("1. Лазерний меч (+15 до шкоди)");
        System.out.println("2. Бластер (+10 до шкоди)");
        System.out.println("3. Плазмова гармата (+20 до шкоди)");
        System.out.print("Введіть номер зброї: ");
        int weaponChoice = getUserChoice();
        scanner.nextLine(); // Очистити перехід на новий рядок

        Weapon weapon = null;
        switch (weaponChoice) {
            case 1:
                weapon = new Weapon("Лазерний меч", 15);
                break;
            case 2:
                weapon = new Weapon("Бластер", 10);
                break;
            case 3:
                weapon = new Weapon("Плазмова гармата", 20);
                break;
            default:
                System.out.println("Неправильний вибір зброї.");
                return;
        }
        droid.equipWeapon(weapon);
    }

    // Метод для відображення списку дроїдів
    private static void showDroids() {
        if (droidList.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
            return;
        }
        System.out.println("\n--- Список дроїдів ---");
        for (int i = 0; i < droidList.size(); i++) {
            Droid d = droidList.get(i);
            System.out.println((i + 1) + ". " + d.getName() + " - Здоров'я: " + d.getHealth());
        }
    }

    // Метод для запуску бою 1 на 1
    private static void startOneOnOneBattle() {
        if (droidList.size() < 2) {
            System.out.println("Недостатньо дроїдів для бою.");
            return;
        }
        System.out.println("\nВиберіть першого дроїда:");
        for (int i = 0; i < droidList.size(); i++) {
            System.out.println((i + 1) + ". " + droidList.get(i).getName());
        }
        System.out.print("Введіть номер дроїда:");
        int firstChoice = getUserChoice() - 1;

        System.out.println("Виберіть другого дроїда:");
        for (int i = 0; i < droidList.size(); i++) {
            if (i != firstChoice) {
                System.out.println((i + 1) + ". " + droidList.get(i).getName());
            }
        }
        System.out.print("Введіть номер дроїда:");
        int secondChoice = getUserChoice() - 1;

        Droid droid1 = droidList.get(firstChoice);
        Droid droid2 = droidList.get(secondChoice);
        Battle.oneOnOne(droid1, droid2);
    }

    // Метод для запуску командного бою
    private static void startTeamBattle() {
        if (droidList.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою (потрібно мінімум 4).");
            return;
        }
        // Формуємо команди
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();
        System.out.println("Формування команд.");
        for (int i = 0; i < droidList.size(); i++) {
            System.out.println((i + 1) + ". " + droidList.get(i).getName());
        }
        scanner.nextLine(); // Очистити перехід на новий рядок

        System.out.println("Додайте дроїдів до команди 1 (введіть номери через кому):");
        String team1Input = scanner.nextLine();
        System.out.println("Додайте дроїдів до команди 2 (введіть номери через кому):");
        String team2Input = scanner.nextLine();

        // Парсинг введення та заповнення команд
        try {
            for (String s : team1Input.split(",")) {
                int idx = Integer.parseInt(s.trim()) - 1;
                team1.add(droidList.get(idx));
            }
            for (String s : team2Input.split(",")) {
                int idx = Integer.parseInt(s.trim()) - 1;
                team2.add(droidList.get(idx));
            }
        } catch (Exception e) {
            System.out.println("Помилка для формування команд.");
            return;
        }

        Battle.teamBattle(team1, team2);
    }
}