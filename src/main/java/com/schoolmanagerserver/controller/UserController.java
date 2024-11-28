package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.query.UserLoginQuery;
import com.schoolmanagerserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // 登录成功，返回token，token中包含用户的角色
    @PostMapping("/login")
    public Result<UserLoginQuery> login(@RequestBody Map<String, String> loginData) {
        logger.info("尝试登录: {}", loginData.get("username"));

        // 调用用户服务进行登录验证
        Result<UserLoginQuery> result = userService.login(loginData.get("username"), loginData.get("password"));
        logger.info("尝试登录的信息"+loginData.toString());
        if (result.data != null) {
            logger.info("登录成功: {}", loginData.get("username"));
        } else {
            logger.warn("登录失败: {}", loginData.get("username"));
        }

        return result;
    }

    @PostMapping("/register")
    public <E> Result<E> register(String username, String password, String repeat) {
        logger.info("Register attempt for username: {}", username);

        // 调用用户服务进行注册处理
        Result<E> result = userService.register(username, password, repeat);

        if (result.data != null) {
            logger.info("Registration successful for username: {}", username);
        } else {
            logger.warn("Registration failed for username: {}", username);
        }

        return result;
    }
}
