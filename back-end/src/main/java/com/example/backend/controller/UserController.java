package com.example.backend.controller;

import com.example.backend.common.interceptor.AuthAccess;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.PwdRequest;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;


    @AuthAccess
    @PostMapping("/login")
    public Result login(@RequestBody UserRequest userItem) {
        User user = userService.login(userItem);
        return Result.success(user);
    }

    @PostMapping("/logout/{userId}")
    public Result logout(@PathVariable Integer userId) {
        userService.logout(userId);
        return Result.success("已退出登录");
    }

    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody UserRequest userItem) {
        userService.register(userItem);
        return Result.success("注册成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserRequest userItem) {
        User user = userService.update(userItem);
        return Result.success(user);
    }

    @PutMapping("/update-pwd")
    public Result updatePwd(@RequestBody PwdRequest pwdItem) {
        userService.updatePwd(pwdItem);
        return Result.success("修改成功，请重新登录");
    }

    @GetMapping("/search/{id}")
    public Result search(@PathVariable Integer id) {
        User user = userService.search(id);
        return Result.success(user);
    }


}
