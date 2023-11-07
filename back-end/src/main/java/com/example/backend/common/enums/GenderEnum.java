package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum GenderEnum {
    MALE("male", "男"),
    FEMALE("female", "女");

    private final String code;
    private final String des;
}
