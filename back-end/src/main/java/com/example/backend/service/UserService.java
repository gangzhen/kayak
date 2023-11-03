package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.utils.CustomDateUtils;
import com.example.backend.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserMapper userMapper;

    private static final String USER_NAME_PATTERN = "^([\\u4e00-\\u9fa5]{2,20}|[a-zA-Z.\\s]{2,20})$";
    private static final String ID_NUM_PATTERN = "^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[0-2])(([0-2][1-9])|10|20|30|31)\\d{3}(\\d|X|x)$";
    private static final String PASSWORD_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$).{6,18}$";
    private static final String PHONE_NUM_PATTERN = "^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$";

    public User login(UserRequest userItem) {

        checkLoginInfo(userItem);

        int year = CustomDateUtils.getCurrentYear();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number", userItem.getIdNumber())
                .eq("year", year);
        List<User> list = this.list(queryWrapper);
        User queryUser = !list.isEmpty() ? list.get(0) : new User();

        if (list.isEmpty() || !userItem.getPassword().equals(queryUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }

        String token = TokenUtils.generateToken(queryUser.getId().toString(), queryUser.getPassword());
        queryUser.setToken(token);
        //存储token
        updateById(queryUser);
        //清空密码 返回前端
        queryUser.setPassword(null);
        return queryUser;
    }

    public void register(UserRequest userItem) {

        checkRegisterInfo(userItem);

        int year = CustomDateUtils.getCurrentYear();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id")
                .eq("id_number", userItem.getIdNumber())
                .eq("year", year);
        List<User> list = this.list(queryWrapper);

        //查询
        if (!list.isEmpty()) {
            throw new ServiceException("账户已注册");
        }

        User user = new User();
        BeanUtils.copyProperties(userItem, user);
        user.setYear(year);
        save(user);
    }

    private void checkLoginInfo(UserRequest userItem) {
        if (StrUtil.isBlank(userItem.getIdNumber()) || StrUtil.isBlank(userItem.getPassword())) {
            throw new ServiceException("登录信息有空值");
        }
    }

    private void checkRegisterInfo(UserRequest userItem) {
        if (StrUtil.isBlank(userItem.getUsername()) || StrUtil.isBlank(userItem.getPassword()) || StrUtil.isBlank(userItem.getConfirmPassword()) || StrUtil.isBlank(userItem.getIdNumber()) || StrUtil.isBlank(userItem.getPhoneNumber()) || StrUtil.isBlank(userItem.getRole())) {
            throw new ServiceException("注册信息有空值");
        }

        if (!checkUsername(userItem.getUsername())) {
            throw new ServiceException("姓名格式有误");
        }

        if (!checkIdNumber(userItem.getIdNumber())) {
            throw new ServiceException("身份证号格式有误");
        }

        if (!checkPassword(userItem.getPassword())) {
            throw new ServiceException("密码格式有误");
        }

        if (!StrUtil.equals(userItem.getPassword(), userItem.getConfirmPassword())) {
            throw new ServiceException("两次输入密码不一致");
        }

        if (!checkPhoneNumber(userItem.getPhoneNumber())) {
            throw new ServiceException("手机号格式有误");
        }
    }

    private boolean checkUsername(String username) {
        Pattern regexPattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher matcher = regexPattern.matcher(username);
        return matcher.matches();
    }

    private boolean checkIdNumber(String idNumber) {
        Pattern regexPattern = Pattern.compile(ID_NUM_PATTERN);
        Matcher matcher = regexPattern.matcher(idNumber);
        return matcher.matches();
    }

    private boolean checkPassword(String password) {
        Pattern regexPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = regexPattern.matcher(password);
        return matcher.matches();
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        Pattern regexPattern = Pattern.compile(PHONE_NUM_PATTERN);
        Matcher matcher = regexPattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public void logout(Integer userId) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", userId);
        userUpdateWrapper.set("token", null);
        update(userUpdateWrapper);
    }
}
