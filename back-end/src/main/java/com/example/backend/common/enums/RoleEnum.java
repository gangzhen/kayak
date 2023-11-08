package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RoleEnum {

    ADMIN("admin", "管理员", -1),
    ATHLETE("athlete", "运动员", 1),
    COACH("coach", "教练员", 2),

    ;

    /**
     * 角色code
     */
    private final String code;
    /**
     * 角色
     */
    private final String role;
    /**
     * 角色权限
     */
    private final Integer permissions;
}
