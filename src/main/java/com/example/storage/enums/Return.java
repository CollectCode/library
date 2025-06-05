package com.example.storage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Return {
    RETURNED("반납완료"),
    LOANING("대출중"),
    LATE("연체중"),
    RETURN_LATE("연체반납");

    private final String description;
}
