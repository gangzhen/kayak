package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum TotalCodeEnum {
    ONE(1, "32名以上 (≥32)"),
    TWO(2, "32名以下 (<32)"),

    ;

    private final Integer code;
    private final String des;
}
