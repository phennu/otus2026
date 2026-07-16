package ru.otus.animals;

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

    public static final List<String> VALUES = collectValues();

    private static List<String> collectValues(){
        List<String> result = new ArrayList<>();
        for (Color type : Color.values()){
            result.add(type.value);
        }
        return result;
    }

    public static Color fromString(String value){
        if (value == null){
            return null;
        }
        return Color.valueOf(value.toUpperCase().trim());
    }

    public String getValue(){
        return value;
    }

}

