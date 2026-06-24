package ru.otus;

import ru.otus.animals.Animal;
import ru.otus.animals.Color;
import ru.otus.factory.AnimalFactory;
import ru.otus.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        List<Animal> animals = new ArrayList<>();
        AnimalFactory factory = new AnimalFactory();
        Scanner scanner = new Scanner(System.in);

        Command currentCommand;
        do{
            currentCommand = askForCommand(scanner);
            if (currentCommand == Command.LIST){
                if(animals.isEmpty()){
                    System.out.println("Список пуст");
                }
                for(Animal animal : animals){
                    System.out.println(animal);
                }

            }else if (currentCommand == Command.ADD){
                AnimalType animalType = askForAnimalType(scanner);
                Animal animal = factory.create(animalType);
                animal.setName(askForName(scanner));
                animal.setAge(askForAge(scanner));
                animal.setWeight(askForWeight(scanner));
                animal.setColor(askForColor(scanner));
                animals.add(animal);
                animal.say();
            }

        }while (currentCommand != Command.EXIT);

    }
    private static Command askForCommand(Scanner scanner){
        String input = null;
        do {
            if (input != null){
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.printf("Введите одну из команд (%s)", String.join("/", Command.VALUES));
            input = scanner.next();
        }
        while (Command.doesNotContain(input));
        return Command.fromString(input);
    }

    private static AnimalType askForAnimalType(Scanner scanner){
        String input = null;
        do {
            if (input != null){
                System.out.println("Введен неверный тип животного, попробуйте ещё раз");
            }
            System.out.printf("Введите тип животного (%s)", String.join("/", AnimalType.VALUES));
            input = scanner.next();
        }
        while (AnimalType.doesNotContain(input));
        return AnimalType.fromString(input);
    }

    private static int askForAge(Scanner scanner) {
        int input = 0;
        do {
            System.out.print("Введите возраст животного: ");
            try{
                input = Integer.parseInt(scanner.next());
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

    private static int askForWeight(Scanner scanner) {
        int input = 0;
        do {
            System.out.print("Введите вес животного: ");
            try{
                input = Integer.parseInt(scanner.next());
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

    private static String askForName(Scanner scanner){
        String input = null;
        do{
            System.out.print("Введите имя животного: ");
            input = scanner.next().trim();
            if (input == null){
                System.out.println("Имя животного не может быть пустым");
            }
        }while(input.isEmpty());
        return input;
    }
   int test = 0;
    private static Color askForColor(Scanner scanner){
        Color color = null;
        do {
            System.out.println("Введите цвет животного: " );
            String input = scanner.next().trim();
            for (Color type : Color.values()){
                if(input.toLowerCase().equals(type.getValue())){
                    color = type;
                    break;
                }
            }
            if(color == null){
                System.out.println("Выберите цвет из списка:");
                for (Color type: Color.values()){
                    System.out.print(type.getValue() + ",");
                }
            }
        }
        while (color == null);
        return color;
    }
}
