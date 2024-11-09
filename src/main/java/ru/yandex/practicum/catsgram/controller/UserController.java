package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.model.UserStorage;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserStorage userStorage;

    public UserController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping
    public Map<String, User> getUsers() {
        return userStorage.getUserMap();
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        if (userStorage.searchUser(user.getEmail()) != null) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        user.setId(getNextId());
        user.setRegistrationDate(System.currentTimeMillis());
        userStorage.addUser(user);

        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        User existingUser = userStorage.searchUser(user.getEmail());
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail()) && userStorage.searchUser(user.getEmail()) != null) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        userStorage.addUser(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    private long getNextId() {
        return System.currentTimeMillis();
    }
}
