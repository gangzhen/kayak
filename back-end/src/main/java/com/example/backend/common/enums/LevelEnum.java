package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum LevelEnum {

    A2000("A2000", "A2000"),
    A1600("A1600", "A1600"),
    A1200("A1200", "A1200"),
    A900("A900", "A900"),
    B800("B800", "B800"),
    B600("B600", "B600"),
    B500("B500", "B500"),
    C400("C400", "C400"),
    C200("C200", "C200"),
    C100("C100", "C100"),

    ;

    private final String code;
    private final String des;

}
