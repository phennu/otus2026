package ru.otus.animals;

import ru.otus.factory.AnimalType;

public class Cat extends Animal{
    @Override
    public void say(){
        System.out.println("Мяу");
    }

    @Override
    public AnimalType getType() {
        return AnimalType.CAT;
    }
}
