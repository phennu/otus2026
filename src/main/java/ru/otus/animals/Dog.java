package ru.otus.animals;

import ru.otus.factory.AnimalType;

public class Dog extends Animal{
    @Override
    public void say(){
        System.out.println("Гав");
    }
    @Override
    public AnimalType getType() {
        return AnimalType.DOG;
    }
}
