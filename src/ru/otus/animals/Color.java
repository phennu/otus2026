package ru.otus.animals;

import ru.otus.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;

public enum Color {
    WHITE("белый"),
    BLACK("черный"),
    ORANGE("оранжевый"),
    GREY("серый"),
    BROWN("коричневый");

    private final String value;

    Color(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
