package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Long registrationDate;

    @NonNull
    private Details details;

    public boolean isValid() {
        return email != null && username != null && details != null;
    }
}
