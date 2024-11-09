package ru.yandex.practicum.catsgram.model;

import java.util.Scanner;

public class UserStorage {

    private final Scanner scanner = new Scanner(System.in);

    private final Storage storage = new MemoryStorage();

    public static void main(String[] args) {
        new UserStorage().loop();
    }

    public void loop() {
        while (true) {
            final String action = getAction();
            switch (action) {
                case "1" -> addUser();
                case "2" -> searchUser();
                case "3" -> {
                    System.out.println("Завершение программы.");
                    return;
                }
                default -> System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private void addUser() {
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
            storage.put(user);
            System.out.println("Пользователь успешно добавлен.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void searchUser() {
        System.out.println("Введите электронную почту для поиска => ");
        final String email = scanner.nextLine().trim();
        final User user = storage.get(email);
        if (user == null) {
            System.out.println("Пользователь не найден.");
        } else {
            System.out.println("Найден пользователь: " + user);
        }
    }

    private String getAction() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Добавление пользователя");
        System.out.println("2 - Поиск пользователя по электронной почте");
        System.out.println("3 - Выход");
        return scanner.nextLine().trim();
    }
}