package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class Details {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private Gender gender;

    // Измените видимость конструктора на public
    public Details(@NonNull String firstName, @NonNull String lastName, @NonNull String email,
                   String phoneNumber, Date birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
