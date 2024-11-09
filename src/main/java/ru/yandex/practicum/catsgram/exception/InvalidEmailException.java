package ru.yandex.practicum.catsgram.exception;

// Исключение для случая, когда email не указан
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}

