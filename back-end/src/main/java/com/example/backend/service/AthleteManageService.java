package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.enums.RankingLevelPointsEnum;
import com.example.backend.controller.request.ConditionItem;
import com.example.backend.entity.UserPoints;
import com.example.backend.mapper.UserPointsMapper;
import com.example.backend.utils.CustomDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AthleteManageService extends ServiceImpl<UserPointsMapper, UserPoints> {

    @Resource
    UserPointsMapper userPointsMapper;

    public void add(UserPoints userPoints) {
        userPoints = setUserPointValue(userPoints);
        save(userPoints);
    }

    public void update(UserPoints userPoints) {
        userPoints = setUserPointValue(userPoints);
        updateById(userPoints);
    }

    public Page<UserPoints> searchUserPoints(ConditionItem conditionItem) {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(conditionItem.getName())) {
            queryWrapper.like("name", conditionItem.getName());
        }
        return this.page(new Page<>(conditionItem.getPage(), conditionItem.getPageSize()), queryWrapper);
    }

    public UserPoints setUserPointValue(UserPoints userPoints) {
        RankingLevelPointsEnum rankingPoints = RankingLevelPointsEnum.getRankingPoints(userPoints.getLevel(), userPoints.getTotalCode(), userPoints.getRankingCode());


        userPoints.setPoints((long) rankingPoints.getPoints());
        userPoints.setTotalCode(rankingPoints.getTotalCode());
        userPoints.setTotalLevel(rankingPoints.getTotalLevel());
        userPoints.setRankingCode(rankingPoints.getRankingCode());
        userPoints.setRankingLevel(rankingPoints.getRankingLevel());
        userPoints.setYear(CustomDateUtils.getCurrentYear());
        return userPoints;
    }
}
