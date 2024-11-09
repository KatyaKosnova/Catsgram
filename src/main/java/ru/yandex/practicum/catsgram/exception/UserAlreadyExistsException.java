package ru.yandex.practicum.catsgram.exception;

// Исключение для случая, когда пользователь уже существует
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
