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
import java.util.ArrayList;
import java.util.List;

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

        UserPoints userPoints01 = new UserPoints();
        userPoints01.setRank(1);
        userPoints01.setName("王*");
        userPoints01.setRegion("地区一");
        userPoints01.setPoints(2200L);
        UserPoints userPoints02 = new UserPoints();
        userPoints02.setRank(1);
        userPoints02.setName("孙*");
        userPoints02.setRegion("地区一");
        userPoints02.setPoints(1100L);
        UserPoints userPoints03 = new UserPoints();
        userPoints03.setRank(1);
        userPoints03.setName("周*");
        userPoints03.setRegion("地区一");
        userPoints03.setPoints(1100L);
        UserPoints userPoints04 = new UserPoints();
        userPoints04.setRank(1);
        userPoints04.setName("武*");
        userPoints04.setRegion("地区一");
        userPoints04.setPoints(1100L);
        UserPoints userPoints05 = new UserPoints();
        userPoints05.setRank(1);
        userPoints05.setName("郑*");
        userPoints05.setRegion("地区一");
        userPoints05.setPoints(1100L);

        List<UserPoints> demo = new ArrayList<>();
        demo.add(userPoints01);
        demo.add(userPoints02);
        demo.add(userPoints03);
        demo.add(userPoints04);
        demo.add(userPoints05);

        result.setRecords(demo);
        result.setTotal(5);


        return result;

    }

}
