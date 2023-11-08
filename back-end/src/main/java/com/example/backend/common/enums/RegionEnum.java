package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RegionEnum {
    REGION01("region1", "地区一"),
    REGION02("region2", "地区二"),
    REGION03("region3", "地区三"),
    REGION04("region4", "地区四"),

    ;

    private final String code;
    private final String des;
}
