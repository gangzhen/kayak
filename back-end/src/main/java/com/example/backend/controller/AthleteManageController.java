package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.ConditionItem;
import com.example.backend.entity.UserPoints;
import com.example.backend.service.AthleteManageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/athlete-manage")
public class AthleteManageController {

    @Resource
    AthleteManageService athleteManageService;

    @PostMapping("/add")
    public Result add(@RequestBody UserPoints userPoints) {
        athleteManageService.add(userPoints);
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        athleteManageService.removeById(id);
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserPoints userPoints) {
        athleteManageService.update(userPoints);
        return Result.success("编辑成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody ConditionItem conditionItem) {
        Page<UserPoints> pageList = athleteManageService.searchUserPoints(conditionItem);
        return Result.success(pageList);
    }
}
