package com.example.backend.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.backend.common.enums.RoleApisEnum;
import com.example.backend.entity.User;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.utils.TokenUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURL().toString();
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                return true;
            }
        }

        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }

        String userId;

        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "请登录");
        }

        User user = userMapper.selectById(Integer.valueOf(userId));
        // 和数据库token进行对比

        if (user == null || !token.equals(user.getToken())) {
            throw new ServiceException("401", "请登录");
        }

        //判断用户角色权限是否可以访问接口
        if (RoleApisEnum.checkAccessUrl(user.getRole(), url)) {
            throw new ServiceException("403", "无权限访问");
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        try {
            jwtVerifier.verify(token);
            String newToken = this.refreshToken(token, user);
            // 将新token放入响应头
            response.setHeader("X-New-Role", user.getRole());
            response.setHeader("X-New-Token", newToken);
            response.setHeader("Access-Control-Expose-Headers", "X-New-Token, X-New-Role");
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "请登录");
        }

        return true;
    }


    private String refreshToken(String token, User user) {
        boolean flag = TokenUtils.judgeRefreshToken(token);
        if (flag) {
            // 生成新的token
            token = TokenUtils.generateToken(user.getId().toString(), user.getPassword());
            // 将新token更新入库
            user.setToken(token);
            userMapper.updateById(user);
        }
        return token;
    }
}
