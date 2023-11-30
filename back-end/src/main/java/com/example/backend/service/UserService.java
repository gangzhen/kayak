package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.enums.RoleEnum;
import com.example.backend.controller.request.PwdRequest;
import com.example.backend.controller.request.UserRequest;
import com.example.backend.controller.response.AthleteDetailResponse;
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

        //TODO 没有权限管理，故此处在注册时不能注册管理员用户
        if (RoleEnum.ADMIN.getCode().equals(userItem.getRole())) {
            throw new ServiceException("不能注册管理员账户");
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

        if (checkUsername(userItem.getUsername())) {
            throw new ServiceException("姓名格式有误");
        }

        if (checkIdNumber(userItem.getIdNumber())) {
            throw new ServiceException("身份证号格式有误");
        }

        if (checkPassword(userItem.getPassword())) {
            throw new ServiceException("密码格式有误");
        }

        if (!StrUtil.equals(userItem.getPassword(), userItem.getConfirmPassword())) {
            throw new ServiceException("两次输入密码不一致");
        }

        if (checkPhoneNumber(userItem.getPhoneNumber())) {
            throw new ServiceException("手机号格式有误");
        }
    }

    private boolean checkUsername(String username) {
        Pattern regexPattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher matcher = regexPattern.matcher(username);
        return !matcher.matches();
    }

    private boolean checkIdNumber(String idNumber) {
        Pattern regexPattern = Pattern.compile(ID_NUM_PATTERN);
        Matcher matcher = regexPattern.matcher(idNumber);
        return !matcher.matches();
    }

    private boolean checkPassword(String password) {
        Pattern regexPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = regexPattern.matcher(password);
        return !matcher.matches();
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        Pattern regexPattern = Pattern.compile(PHONE_NUM_PATTERN);
        Matcher matcher = regexPattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    public void logout(Integer userId) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", userId);
        userUpdateWrapper.set("token", null);
        update(userUpdateWrapper);
    }

    public User update(UserRequest userItem) {

        // 校验提交修改的用户信息符合规范
        checkUpdateInfo(userItem);


        User queryUser = userMapper.selectById(userItem.getId());
        // 校验用户操作是否合法 不合法抛异常
        checkUserOperationLegal(queryUser, userItem);

        // 更新用户
        queryUser.setUsername(userItem.getUsername());
        queryUser.setIdNumber(userItem.getIdNumber());
        queryUser.setPhoneNumber(userItem.getPhoneNumber());
        queryUser.setRole(userItem.getRole());
        updateById(queryUser);

        // 置空密码
        queryUser.setPassword(null);
        return queryUser;
    }

    public void updateByAdmin(UserRequest userRequest) {

        // 校验提交修改的用户信息符合规范
        checkUpdateInfo(userRequest);


        User queryUser = userMapper.selectById(userRequest.getId());
        // 校验用户操作是否合法 不合法抛异常
        checkUserOperationLegalByAdmin(queryUser, userRequest);

        // 更新用户
        queryUser.setUsername(userRequest.getUsername());
        queryUser.setIdNumber(userRequest.getIdNumber());
        queryUser.setPhoneNumber(userRequest.getPhoneNumber());
        queryUser.setRole(userRequest.getRole());
        updateById(queryUser);
    }

    private void checkUpdateInfo(UserRequest userItem) {

        if (StrUtil.isBlank(userItem.getUsername()) || StrUtil.isBlank(userItem.getPassword()) || StrUtil.isBlank(userItem.getIdNumber()) || StrUtil.isBlank(userItem.getPhoneNumber()) || StrUtil.isBlank(userItem.getRole())) {
            throw new ServiceException("修改信息有空值");
        }

        if (checkUsername(userItem.getUsername())) {
            throw new ServiceException("姓名格式有误");
        }

        if (checkIdNumber(userItem.getIdNumber())) {
            throw new ServiceException("身份证号格式有误");
        }

        if (checkPhoneNumber(userItem.getPhoneNumber())) {
            throw new ServiceException("手机号格式有误");
        }
    }

    private void checkUserOperationLegal(User queryUser, UserRequest userItem) {

        if (queryUser == null) {
            throw new ServiceException("非法修改信息");
        }

        if (!queryUser.getPassword().equals(userItem.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }

        // 非法提权抛异常 （更新提交需要修改的信息是不是管理员，是管理员后对比数据库中的用户信息是不是管理员，相同可更新，不相同拒绝更新）
        if (RoleEnum.ADMIN.getCode().equals(userItem.getRole()) && !RoleEnum.ADMIN.getCode().equals(queryUser.getRole())) {
            throw new ServiceException("非法修改信息");
        }
    }

    private void checkUserOperationLegalByAdmin(User queryUser, UserRequest userRequest) {

        if (queryUser == null) {
            throw new ServiceException("非法修改信息");
        }

        // 非法提权抛异常 （更新提交需要修改的信息是不是管理员，是管理员后对比数据库中的用户信息是不是管理员，相同可更新，不相同拒绝更新）
        if (RoleEnum.ADMIN.getCode().equals(userRequest.getRole()) && !RoleEnum.ADMIN.getCode().equals(queryUser.getRole())) {
            throw new ServiceException("非法修改信息");
        }
    }

    public User search(Integer userId) {
        User queryUser = userMapper.selectById(userId);
        // 置空密码
        queryUser.setPassword(null);
        return queryUser;
    }

    public User search(String idNumber, Integer year) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number", idNumber)
                .eq("year", year);
        List<User> list = this.list(queryWrapper);
        return !list.isEmpty() ? list.get(0) : new User();
    }

    public void updatePwd(PwdRequest pwdItem) {

        // 校验提交修改的用户密码是否符合规范
        checkUpdatePwd(pwdItem);


        User queryUser = userMapper.selectById(pwdItem.getId());
        // 校验用户操作是否合法 不合法抛异常
        checkUserPwdConsistent(queryUser, pwdItem);

        // 更新用户密码并登出用户清除token
        queryUser.setPassword(pwdItem.getNewPwd());
        updateById(queryUser);
        this.logout(queryUser.getId());
    }

    private void checkUpdatePwd(PwdRequest pwdItem) {

        if (StrUtil.isBlank(pwdItem.getId()) || StrUtil.isBlank(pwdItem.getId()) || StrUtil.isBlank(pwdItem.getId()) || StrUtil.isBlank(pwdItem.getId())) {
            throw new ServiceException("修改密码有空值");
        }

        if (checkPassword(pwdItem.getNewPwd())) {
            throw new ServiceException("密码格式有误");
        }

        if (!StrUtil.equals(pwdItem.getNewPwd(), pwdItem.getConfirmPwd())) {
            throw new ServiceException("两次输入密码不一致");
        }
    }

    private void checkUserPwdConsistent(User queryUser, PwdRequest pwdItem) {

        if (queryUser == null) {
            throw new ServiceException("非法修改");
        }

        if (!queryUser.getPassword().equals(pwdItem.getOldPwd())) {
            throw new ServiceException("用户名或密码错误");
        }

    }

    public AthleteDetailResponse searchAthleteDetail(Integer id) {
        User searchUser = search(id);
        if (searchUser.getId() == null) {
            throw new ServiceException("没有用户信息");
        }
        return new AthleteDetailResponse(searchUser.getId(), searchUser.getUsername(), searchUser.getIdNumber());
    }

    public AthleteDetailResponse searchAthleteDetail(String idNumber, Integer year) {
        User searchUser = search(idNumber, year);
        if (searchUser.getId() == null) {
            throw new ServiceException("没有用户信息");
        }
        return new AthleteDetailResponse(searchUser.getId(), searchUser.getUsername(), searchUser.getIdNumber());
    }
}
