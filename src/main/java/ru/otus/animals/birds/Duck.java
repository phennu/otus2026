package ru.otus.animals.birds;

import ru.otus.animals.Animal;
import ru.otus.factory.AnimalType;

public class Duck extends Animal implements Flying {
    public void fly(){
        System.out.println("Я лечу");
    }

    @Override
    public void say(){
        System.out.println("Кря");
    }

    @Override
    public AnimalType getType() {
        return AnimalType.DUCK;
    }
}
