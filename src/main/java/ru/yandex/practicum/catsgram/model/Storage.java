package ru.yandex.practicum.catsgram.model;

public interface Storage {

    void put(User user);

    User get(String email);
}