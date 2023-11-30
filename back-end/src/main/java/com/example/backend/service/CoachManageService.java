package com.example.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.enums.RoleEnum;
import com.example.backend.controller.request.CoachCondition;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoachManageService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserService userService;


    private static final String DEFAULT_PASSWD = "1234abcd";

    public void add(UserRequest userRequest) {
        //默认密码
        userRequest.setPassword(DEFAULT_PASSWD);
        userRequest.setConfirmPassword(DEFAULT_PASSWD);
        //注册用户
        userService.register(userRequest);
    }

    public void delete(Integer id) {
        if (id == null) {
            throw new ServiceException("非法值删除");
        }
        removeById(id);
    }

    public void update(UserRequest userRequest) {
        if (!RoleEnum.COACH.getCode().equals(userRequest.getRole())) {
            throw new ServiceException("非法修改角色");
        }
        // 校验提交修改的用户信息符合规范
        userService.updateByAdmin(userRequest);
    }

    public Page<User> searchCoaches(CoachCondition coachCondition) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "id_number", "phone_number", "role", "year")
                .eq("role", RoleEnum.COACH.getCode());
        if (coachCondition.getYear() != null && coachCondition.getYear() > 0) {
            queryWrapper.eq("year", coachCondition.getYear());
        }
        return this.page(new Page<>(coachCondition.getPage(), coachCondition.getPageSize()), queryWrapper);
    }
}
