package ru.sidorov.currencyproject.exception;

public class EntityNotFoundException extends RuntimeException{
    String message;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
