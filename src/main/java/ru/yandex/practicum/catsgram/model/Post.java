package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.NonNull;
import java.time.Instant;

@Data
public class Post {

    private Long id;               // ID поста
    private long authorId;         // ID пользователя, который создал пост
    private String description;    // Описание поста
    private Instant postDate;      // Дата и время публикации поста

    // Конструктор, который позволяет создавать объект с обязательными полями
    public Post(@NonNull Long id, @NonNull Long authorId, @NonNull String description, @NonNull Instant postDate) {
        this.id = id;
        this.authorId = authorId;
        setDescription(description);  // Использование метода для проверки пустого описания
        this.postDate = postDate;
    }

    // Метод для установки описания с проверкой на пустое значение
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        this.description = description;
    }

    // Пример использования метода toString() для красивого вывода
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", description='" + description + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
