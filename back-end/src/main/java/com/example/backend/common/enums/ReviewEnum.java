package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ReviewEnum {

    PENDING("pending", "待审核"),
    PASS("pass", "审核通过"),
    FAIL("fail", "审核不通过"),

    ;

    private final String code;
    private final String des;

    public static ReviewEnum getStatus(String code) {
        for (ReviewEnum item : ReviewEnum.values()) {
            if (item.code.equals(code)) {
                // 如果url包含禁止列表，那么不允许用户访问
                return item;
            }
        }
        return null;
    }
}
