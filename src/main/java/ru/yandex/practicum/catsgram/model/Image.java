package ru.yandex.practicum.catsgram.model;

import lombok.Data;

@Data // Lombok генерирует getters, setters, toString, equals и hashCode
public class Image {

    private Long id;

    private long postId; // ID поста, к которому прикреплено изображение

    private String originalFileName;

    private String filePath;
}