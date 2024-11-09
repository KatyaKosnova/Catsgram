package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

// Информация о покупках пользователя
@Value
@Builder
public class PurchasesInformation {

    private Date lastPurchase;

    @Builder.Default
    private long purchaseCounts = 0;
}