package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@ToString
public enum RoleApisEnum {
    //TODO 角色需要添加禁止访问接口的时候修改
    ADMIN(RoleEnum.ADMIN.getCode(),
            new String[]{
            }),
    ATHLETE(RoleEnum.ATHLETE.getCode(),
            new String[]{
                    ApisEnum.ATHLETE_MANAGE_ADD.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_DELETE.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_UPDATE.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_SEARCH.getUrl(),

                    ApisEnum.REGIONAL_COMPETITION_ADD.getUrl(),
                    ApisEnum.REGIONAL_COMPETITION_DELETE.getUrl(),
                    ApisEnum.REGIONAL_COMPETITION_UPDATE.getUrl(),

                    ApisEnum.COMPETITION_REGISTRATION_REVIEW.getUrl(),

                    ApisEnum.COACH_MANAGE_ADD.getUrl(),
                    ApisEnum.COACH_MANAGE_DELETE.getUrl(),
                    ApisEnum.COACH_MANAGE_UPDATE.getUrl(),
            }),
    COACH(RoleEnum.COACH.getCode(),
            new String[]{
                    ApisEnum.ATHLETE_MANAGE_ADD.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_DELETE.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_UPDATE.getUrl(),
                    ApisEnum.ATHLETE_MANAGE_SEARCH.getUrl(),

                    ApisEnum.REGIONAL_COMPETITION_ADD.getUrl(),
                    ApisEnum.REGIONAL_COMPETITION_DELETE.getUrl(),
                    ApisEnum.REGIONAL_COMPETITION_UPDATE.getUrl(),

                    ApisEnum.COMPETITION_REGISTRATION_REGISTRATION.getUrl(),
                    ApisEnum.COMPETITION_REGISTRATION_CANCEL.getUrl(),
                    ApisEnum.COMPETITION_REGISTRATION_SEARCH.getUrl(),
                    ApisEnum.COMPETITION_REGISTRATION_REVIEW.getUrl(),
                    ApisEnum.COMPETITION_REGISTRATION_SEARCH_COMPETITION.getUrl(),
                    ApisEnum.COMPETITION_REGISTRATION_SEARCH_ATHLETE_DETAIL.getUrl(),

                    ApisEnum.COACH_MANAGE_ADD.getUrl(),
                    ApisEnum.COACH_MANAGE_DELETE.getUrl(),
                    ApisEnum.COACH_MANAGE_UPDATE.getUrl(),
            }),


    ;


    private final String code;
    /**
     * 无权限访问的接口
     */
    private final String[] noPermissions;

    public static boolean checkNoPermissionUrl(String role, String url) {
        for (RoleApisEnum item : RoleApisEnum.values()) {
            if (item.code.equals(role)) {
                // 如果url包含禁止列表，那么不允许用户访问
                return Arrays.stream(item.noPermissions).anyMatch(url::contains);
            }
        }
        return false;
    }
}
