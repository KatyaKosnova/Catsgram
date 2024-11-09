package ru.yandex.practicum.catsgram.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserStorage implements Storage {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void put(User user) {
        if (userMap.containsKey(user.getDetails().getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        userMap.put(user.getDetails().getEmail(), user);
    }

    @Override
    public User get(String email) {
        return userMap.get(email);
    }

    public void addUser(User user) {
        put(user); // Используем метод put, чтобы избежать дублирования кода
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public User searchUser(String email) {
        return get(email);
    }

    public void loop() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                final String action = getAction(scanner);
                switch (action) {
                    case "1" -> addUserConsole(scanner);
                    case "2" -> searchUserConsole(scanner);
                    case "3" -> {
                        System.out.println("Завершение программы.");
                        return;
                    }
                    default -> System.out.println("Неверный ввод. Попробуйте снова.");
                }
            }
        }
    }

    private void addUserConsole(Scanner scanner) {
        System.out.println("Введите электронную почту пользователя => ");
        final String email = scanner.nextLine().trim();

        System.out.println("Введите имя пользователя => ");
        final String firstName = scanner.nextLine().trim();

        System.out.println("Введите фамилию пользователя => ");
        final String lastName = scanner.nextLine().trim();

        final Details details = Details.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        try {
            final User user = User.builder().details(details).build();
            addUser(user);
            System.out.println("Пользователь успешно добавлен.");
        } catch (UserAlreadyExistsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void searchUserConsole(Scanner scanner) {
        System.out.println("Введите электронную почту для поиска => ");
        final String email = scanner.nextLine().trim();
        final User user = searchUser(email);
        if (user == null) {
            System.out.println("Пользователь не найден.");
        } else {
            System.out.println("Найден пользователь: " + user);
        }
    }

    private String getAction(Scanner scanner) {
        System.out.println("Выберите действие:");
        System.out.println("1 - Добавление пользователя");
        System.out.println("2 - Поиск пользователя по электронной почте");
        System.out.println("3 - Выход");
        return scanner.nextLine().trim();
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
}
