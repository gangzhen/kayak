package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.Result;
import com.example.backend.controller.request.Condition;
import com.example.backend.controller.request.ConditionItem;
import com.example.backend.controller.response.ChartResponse;
import com.example.backend.entity.UserPoints;
import com.example.backend.service.UserPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/points")
public class UserPointsController {

    @Resource
    UserPointsService userPointsService;

    @PostMapping("/add")
    public Result add(@RequestBody UserPoints userPoints) {
        userPointsService.add(userPoints);
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userPointsService.removeById(id);
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserPoints userPoints) {
        userPointsService.update(userPoints);
        return Result.success("编辑成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody ConditionItem conditionItem) {
        Page<UserPoints> pageList = userPointsService.searchUserPoints(conditionItem);
        return Result.success(pageList);
    }

    @PostMapping("/search-rank")
    public Result searchRank(@RequestBody Condition condition) {
        Page<UserPoints> pageList = userPointsService.searchUserSumPoints(condition);
        return Result.success(pageList);
    }

    @GetMapping("/stay-chart")
    public Result searchStayChart() {
        ChartResponse chartResponse = userPointsService.searchStayChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/stay-rate-chart")
    public Result searchStayRateChart() {
        ChartResponse chartResponse = userPointsService.searchStayRateChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/entry-chart")
    public Result searchEntryChart() {
        ChartResponse chartResponse = userPointsService.searchEntryChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/entry-rate-chart")
    public Result searchEntryRateChart() {
        ChartResponse chartResponse = userPointsService.searchEntryRateChart();
        return Result.success(chartResponse);
    }
}
