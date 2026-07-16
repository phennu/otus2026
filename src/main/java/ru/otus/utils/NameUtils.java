package ru.otus.utils;

public class NameUtils {
    public static boolean isValidName(String value){
        if (value == null){
            return false;
        }
        return value.matches("^[A-Za-zА-ЯЁа-яё]+([ -][A-Za-zА-ЯЁа-яё]+)*$");
    }

    public static boolean isNotValidName(String value){
        return !isValidName(value);
    }
}
