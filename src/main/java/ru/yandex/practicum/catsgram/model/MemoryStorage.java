package ru.yandex.practicum.catsgram.model;

import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage implements Storage {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void put(final User user) {
        if (user == null || user.getDetails() == null || user.getDetails().getEmail() == null) {
            throw new InvalidEmailException("Email должен быть указан.");
        }

        final String email = user.getDetails().getEmail().toLowerCase();

        if (users.containsKey(email)) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }

        users.put(email, user);
    }

    @Override
    public User get(final String email) {
        if (email == null) {
            return null;
        }

        return users.get(email.toLowerCase());
    }
}
