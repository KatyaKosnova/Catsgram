package ru.yandex.practicum.catsgram.model;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage implements Storage {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void put(final User user) {
        if (user.getDetails() == null || user.getDetails().getEmail() == null) {
            throw new RuntimeException("Email should be provided");
        }
        final String email = user.getDetails().getEmail().toLowerCase();
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
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