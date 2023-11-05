package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.Condition;
import com.example.backend.entity.UserPoints;
import com.example.backend.service.PointsRankingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/points-rank")
public class PointsRankingController {

    @Resource
    PointsRankingService pointsRankingService;

    @PostMapping("/search")
    public Result searchRank(@RequestBody Condition condition) {
        Page<UserPoints> pageList = pointsRankingService.searchUserSumPoints(condition);
        return Result.success(pageList);
    }


}
