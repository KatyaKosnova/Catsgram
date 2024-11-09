package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {

    @NonNull
    private Details details;

    @Builder.Default
    private PurchasesInformation purchasesInformation = PurchasesInformation.builder().build();
}