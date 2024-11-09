package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

// Детализированная информация о пользователе
@Data
@Builder
public class Details {

    @NonNull
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String information;

    private Date dayOfBirthday;

    @Builder.Default
    private Gender gender = Gender.UNKNOWN;
}
