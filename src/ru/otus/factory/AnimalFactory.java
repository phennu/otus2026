package ru.otus.factory;

import ru.otus.animals.Animal;
import ru.otus.animals.Cat;
import ru.otus.animals.Dog;
import ru.otus.animals.birds.Duck;

public class AnimalFactory {

    public Animal create (AnimalType type){
        if(type == AnimalType.CAT){
            return new Cat();
        }
        if (type == AnimalType.DOG){
            return new Dog();
        }
        if (type == AnimalType.DUCK){
            return new Duck();
        }
        return null;
    }
}
