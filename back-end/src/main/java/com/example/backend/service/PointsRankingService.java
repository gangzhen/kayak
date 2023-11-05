package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.controller.request.Condition;
import com.example.backend.entity.UserPoints;
import com.example.backend.mapper.UserPointsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PointsRankingService extends ServiceImpl<UserPointsMapper, UserPoints> {

    @Resource
    UserPointsMapper userPointsMapper;

    public Page<UserPoints> searchUserSumPoints(Condition condition) {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "gender", "age", "id_number", "region", "IFNULL(sum(points),0) as 'points'")
                .groupBy("name", "gender", "age", "id_number", "region");
        if (!StrUtil.isBlank(condition.getName())) {
            queryWrapper.like("name", condition.getName());
        }
        if (!StrUtil.isBlank(condition.getGender())) {
            queryWrapper.eq("gender", condition.getGender());
        }
        if (condition.getAge() != 0) {
            queryWrapper.eq("age", condition.getAge());
        }
        if (condition.getYear() != 0) {
            queryWrapper.eq("year", condition.getYear());
        }
        if (!StrUtil.isBlank(condition.getRegion())) {
            queryWrapper.like("region", condition.getRegion());
        }
        if (StrUtil.isBlank(condition.getSort()) || condition.getSort().equals("htl")) {
            queryWrapper.orderByDesc("points");
        } else if (condition.getSort().equals("lth")) {
            queryWrapper.orderByAsc("points");
        }

        Page<UserPoints> result = this.page(new Page<>(condition.getPage(), condition.getPageSize()), queryWrapper);
        int rank = (condition.getPage() - 1) * condition.getPageSize() + 1;

        for (int i = 0; i < result.getRecords().size(); i++) {
            result.getRecords().get(i).setRank(rank);
            rank++;
        }
        return result;

    }

}
