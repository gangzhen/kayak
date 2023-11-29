package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.RegionCondition;
import com.example.backend.entity.Competition;
import com.example.backend.service.RegionalCompetitionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/region-competition")
public class RegionalCompetitionController {

    @Resource
    RegionalCompetitionService regionalCompetitionService;

    @PostMapping("/add")
    public Result add(@RequestBody Competition competition) {
        regionalCompetitionService.add(competition);
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        regionalCompetitionService.delete(id);
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Competition competition) {
        regionalCompetitionService.update(competition);
        return Result.success("编辑成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody RegionCondition regionCondition) {
        Page<Competition> pageList = regionalCompetitionService.searchRegionalCompetition(regionCondition);
        return Result.success(pageList);
    }

}
