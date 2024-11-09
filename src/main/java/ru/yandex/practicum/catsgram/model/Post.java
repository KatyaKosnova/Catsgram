package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import java.time.Instant;

@Data // Lombok генерирует getters, setters, toString, equals и hashCode
public class Post {

    private Long id;

    private long authorId; // ID пользователя, который создал сообщение

    private String description;

    private Instant postDate;
}