package ru.otus;

import ru.otus.animals.Animal;
import ru.otus.animals.Color;
import ru.otus.database.IDBConnectionManager;
import ru.otus.database.SqlConnectionManager;
import ru.otus.factory.AnimalFactory;
import ru.otus.factory.AnimalType;
import ru.otus.factory.DBFactory;
import ru.otus.tables.AnimalTable;
import ru.otus.utils.NameUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class App {
    private static Scanner scanner;

    public static void main(String[] args) throws SQLException {
        IDBConnectionManager connectionManager = new DBFactory().getConnectionManager("SQL_DB");
        AnimalFactory factory = new AnimalFactory();
        scanner = new Scanner(System.in);
        AnimalTable animalTable = new AnimalTable(factory, connectionManager);

        Command currentCommand;
        do {
            currentCommand = askForCommand();
            if (currentCommand == Command.LIST) {
                List<Animal> animals = animalTable.findAllAnimals();

                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal : animals) {
                    System.out.println(animal);
                }


            } else if (currentCommand == Command.ADD) {
                AnimalType animalType = askForAnimalType();
                Animal animal = factory.create(animalType);
                animal.setName(askForName());
                animal.setAge(askForAge());
                animal.setWeight(askForWeight());
                animal.setColor(askForColor());
                animal.say();
                animalTable.create(animal);
            } else if (currentCommand == Command.FIND){
                AnimalType animalType = askForAnimalType();
                List<Animal> animals = animalTable.findAnimalsByType(animalType);

                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal : animals) {
                    System.out.println(animal);
                }
            }else if (currentCommand == Command.UPDATE){
                String name = askForName();
                Animal animal = animalTable.findAnimalByName(name);
                if (animal == null){
                    System.out.println("Такого животного нет в списке");
                }else {
                    animal.setAge(askForAge());
                    animal.setWeight(askForWeight());
                    animal.setColor(askForColor());
                    animalTable.updateAnimalByName(animal);
                }
            }

        } while (currentCommand != Command.EXIT);
        connectionManager.close();


    }

    private static Command askForCommand() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.printf("Введите одну из команд (%s)", String.join("/", Command.VALUES));
            input = scanner.nextLine();
        }
        while (Command.doesNotContain(input));
        return Command.fromString(input);
    }

    private static AnimalType askForAnimalType() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введен неверный тип животного, попробуйте ещё раз");
            }
            System.out.printf("Введите тип животного (%s)", String.join("/", AnimalType.VALUES));
            input = scanner.nextLine();
        }
        while (AnimalType.doesNotContain(input));
        return AnimalType.fromString(input);
    }

    private static int askForAge() {
        int input = 0;
        do {
            System.out.print("Введите возраст животного: ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Введен неверный возраст, попробуйте ещё раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте ещё раз");
            }
        }
        while (input <= 0);
        return input;
    }

    private static int askForWeight() {
        int input = 0;
        do {
            System.out.print("Введите вес животного: ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Введен неверный вес, попробуйте ещё раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте ещё раз");
            }
        }
        while (input <= 0);
        return input;
    }

    private static String askForName() {
        boolean nameIsNotValid;
        String input;
        do {
            System.out.print("Введите имя животного: ");
            input = scanner.nextLine().trim();
            nameIsNotValid = NameUtils.isNotValidName(input);
            if (nameIsNotValid) {
                System.out.println("Введите корректно имя животного (Допускаются пробелы, тире и буквы латинского и кириллического алфавита)");
            }
        } while (nameIsNotValid);
        return input;
    }

    private static Color askForColor() {
        Color color = null;
        do {
            System.out.printf("Введите цвет животного (%s): ", String.join(", ", Color.VALUES));
            String input = scanner.nextLine().trim();
            for (Color type : Color.values()) {
                if (input.toLowerCase().equals(type.getValue())) {
                    color = type;
                    break;
                }
            }
            if (color == null) {
                System.out.println("Выберите цвет из списка:");
                for (Color type : Color.values()) {
                    System.out.print(type.getValue() + ",");
                }
            }
        }
        while (color == null);
        return color;
    }
}
