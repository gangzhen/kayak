package com.example.backend.controller;

import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.response.ChartResponse;
import com.example.backend.service.ChartsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/charts")
public class ChartsController {

    @Resource
    ChartsService chartsService;

    @GetMapping("/stay")
    public Result searchStayChart() {
        ChartResponse chartResponse = chartsService.searchStayChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/stay-rate")
    public Result searchStayRateChart() {
        ChartResponse chartResponse = chartsService.searchStayRateChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/entry")
    public Result searchEntryChart() {
        ChartResponse chartResponse = chartsService.searchEntryChart();
        return Result.success(chartResponse);
    }

    @GetMapping("/entry-rate")
    public Result searchEntryRateChart() {
        ChartResponse chartResponse = chartsService.searchEntryRateChart();
        return Result.success(chartResponse);
    }
}
