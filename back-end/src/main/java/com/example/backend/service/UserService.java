package com.example.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserMapper userMapper;

    public User login(UserRequest userItem) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id_number", userItem.getIdNumber()).orderByAsc("year");
        List<User> list = this.list(queryWrapper);
        User queryUser = !list.isEmpty() ? list.get(0) : new User();

        if (list.isEmpty() || !queryUser.getPassword().equals(userItem.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }

        return queryUser;
    }
}
