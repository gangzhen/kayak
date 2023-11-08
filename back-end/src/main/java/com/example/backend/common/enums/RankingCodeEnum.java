package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RankingCodeEnum {
    ONE(1, "冠军", new int[]{1, 1}),
    TWO(2, "亚军", new int[]{2, 2}),
    THREE(3, "半决赛", new int[]{3, 4}),
    FOUR(4, "前8名", new int[]{5, 8}),
    FIVE(5, "前16名", new int[]{9, 16}),
    SIX(6, "前32名", new int[]{17, 32}),

    ;

    private final Integer code;
    private final String des;
    private final int[] rankingRange;

}
