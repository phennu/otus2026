package ru.otus.exceptions;

public class DbNotSupported extends RuntimeException{
    public DbNotSupported(String dbType){
        super(String.format("DB type is not supported: %s", dbType));
    }
}
