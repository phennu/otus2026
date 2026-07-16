package ru.otus.tables.interfaces;

import ru.otus.animals.Animal;
import ru.otus.factory.AnimalType;

import java.util.List;

public interface IAnimalTable {
    List<Animal> findAllAnimals();
    List<Animal> findAnimalsByType(AnimalType type);
    void create(Animal animal);
}
