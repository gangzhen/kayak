package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController

@RequestMapping("/login")
public class UserLoginController {

    @Resource
    UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody UserRequest userItem) {
        User user = userService.login(userItem);
        return Result.success(user);
    }
}
