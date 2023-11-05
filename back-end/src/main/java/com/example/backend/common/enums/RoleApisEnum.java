package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@ToString
public enum RoleApisEnum {
    ADMIN(RoleEnum.ADMIN.getCode(), new String[]{}),
    ATHLETE(RoleEnum.ATHLETE.getCode(), new String[]{ApisEnum.ATHLETE_MANAGE_ADD.getUrl(), ApisEnum.ATHLETE_MANAGE_DELETE.getUrl(), ApisEnum.ATHLETE_MANAGE_UPDATE.getUrl(), ApisEnum.ATHLETE_MANAGE_SEARCH.getUrl()}),
    COACH(RoleEnum.COACH.getCode(), new String[]{ApisEnum.ATHLETE_MANAGE_ADD.getUrl(), ApisEnum.ATHLETE_MANAGE_DELETE.getUrl(), ApisEnum.ATHLETE_MANAGE_UPDATE.getUrl(), ApisEnum.ATHLETE_MANAGE_SEARCH.getUrl()});


    private final String code;
    /**
     * 无权限访问的接口
     */
    private final String[] noPermissions;

    public static boolean checkAccessUrl(String role, String url) {
        for (RoleApisEnum item : RoleApisEnum.values()) {
            if (item.code.equals(role)) {
                // 如果url包含禁止列表，那么不允许用户访问
                return Arrays.stream(item.noPermissions).anyMatch(url::contains);
            }
        }
        return false;
    }
}
