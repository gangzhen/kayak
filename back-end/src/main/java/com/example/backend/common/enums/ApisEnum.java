package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ApisEnum {

    USER_LOGIN("/user/login", "登录"),
    USER_LOGOUT("/user/logout", "退出登录"),
    USER_REGISTER("/user/register", "注册"),
    USER_UPDATE("/user/update", "修改"),
    USER_UPDATE_PWD("/user/update-pwd", "修改密码"),
    USER_SEARCH("/user/search", "查找"),
    ATHLETE_MANAGE_ADD("/athlete-manage/add", "新增运动员分数记录"),
    ATHLETE_MANAGE_DELETE("/athlete-manage/delete", "删除运动员分数记录"),
    ATHLETE_MANAGE_UPDATE("/athlete-manage/update", "更新运动员分数记录"),
    ATHLETE_MANAGE_SEARCH("/athlete-manage/search", "查询运动员分数记录列表"),
    POINTS_RANK_SEARCH("/points-rank/search", "查询积分排名列表"),
    CHARTS_STAY("/charts/stay", "留榜人数数据查询"),
    CHARTS_STAY_RATE("/charts/stay-rate", "留榜率数据查询"),
    CHARTS_ENTRY("/charts/entry", "新入榜人数数据查询"),
    CHARTS_ENTRY_RATE("/charts/entry-rate", "新入榜率数据查询"),

    //TODO 添加一个新接口需要更新这个枚举类

    ;
    private final String url;
    private final String apiDesc;


}
