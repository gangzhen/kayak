package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.CoachCondition;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.service.CoachManageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coach-manage")
public class CoachManageController {

    @Resource
    CoachManageService coachManageService;

    @PostMapping("/add")
    public Result add(@RequestBody UserRequest userRequest) {
        coachManageService.add(userRequest);
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        coachManageService.delete(id);
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserRequest userRequest) {
        coachManageService.update(userRequest);
        return Result.success("编辑成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody CoachCondition coachCondition) {
        Page<User> pageList = coachManageService.searchCoaches(coachCondition);
        return Result.success(pageList);
    }
}
