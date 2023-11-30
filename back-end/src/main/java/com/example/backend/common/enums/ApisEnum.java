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

    POINTS_RANK_SEARCH("/points-rank/search", "查询积分排名列表"),

    ATHLETE_MANAGE_ADD("/athlete-manage/add", "新增运动员分数记录"),
    ATHLETE_MANAGE_DELETE("/athlete-manage/delete", "删除运动员分数记录"),
    ATHLETE_MANAGE_UPDATE("/athlete-manage/update", "更新运动员分数记录"),
    ATHLETE_MANAGE_SEARCH("/athlete-manage/search", "查询运动员分数记录列表"),

    REGIONAL_COMPETITION_ADD("/region-competition/add", "新增地区赛事"),
    REGIONAL_COMPETITION_DELETE("/region-competition/delete", "删除地区赛事"),
    REGIONAL_COMPETITION_UPDATE("/region-competition/update", "更新地区赛事"),
    REGIONAL_COMPETITION_SEARCH("/region-competition/search", "查询地区赛事列表"),

    COMPETITION_REGISTRATION_REGISTRATION("/competition-registration/registration", "赛事报名"),
    COMPETITION_REGISTRATION_CANCEL("/competition-registration/cancel", "赛事取消报名"),
    COMPETITION_REGISTRATION_SEARCH("/competition-registration/search", "查找赛事报名列表"),
    COMPETITION_REGISTRATION_REVIEW("/competition-registration/review", "审核赛事报名"),
    COMPETITION_REGISTRATION_SEARCH_COMPETITION("/competition-registration/search-competitions", "按年份查询赛事列表"),
    COMPETITION_REGISTRATION_SEARCH_ATHLETE_DETAIL("/competition-registration/search-athlete-detail", "查询运动员详情"),

    COACH_MANAGE_ADD("/coach-manage/add", "新增教练员"),
    COACH_MANAGE_DELETE("/coach-manage/delete", "删除教练员"),
    COACH_MANAGE_UPDATE("/coach-manage/update", "更新教练员"),
    COACH_MANAGE_SEARCH("/coach-manage/search", "查询教练员列表"),


    CHARTS_STAY("/charts/stay", "留榜人数数据查询"),
    CHARTS_STAY_RATE("/charts/stay-rate", "留榜率数据查询"),
    CHARTS_ENTRY("/charts/entry", "新入榜人数数据查询"),
    CHARTS_ENTRY_RATE("/charts/entry-rate", "新入榜率数据查询"),

    //TODO 每添加一个新接口需要更新这个枚举类

    ;
    private final String url;
    private final String apiDesc;


}
