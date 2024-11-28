package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.controller.UserController;
import com.schoolmanagerserver.mapper.UserMapper;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import com.schoolmanagerserver.query.UserLoginQuery;
import com.schoolmanagerserver.service.UserService;
import com.schoolmanagerserver.utils.JWTUtil;
import com.schoolmanagerserver.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${tokenExpireMs}")
    public int tokenExpireMs;


    @Override
    public Result<UserLoginQuery> login(String username, String password) {
        User user = findByUsername(username).data;

        if (user == null || !MD5Util.getMD5(password).equals(user.getPassword())){
            if (user==null){
                logger.info("用户不存在");
            }
            else
                logger.info("密码不正确或者{}",user.getUsername());
            return Result.fail("用户名或者密码错误");}
        logger.info("查找到的用户密码{}", user.getPassword());
        Map<String, Object> claims = new HashMap<>();//token里面存放的东西
        claims.put("username", username);
        claims.put("id", user.getId());
        claims.put("role", user.getRoleNameList());

        String token = jwtUtil.create(claims);
        jwtUtil.storeToken(token, claims);

        UserLoginQuery u = new UserLoginQuery();
        u.setAccessToken(token);
        u.setUsername(username);
        u.setId(user.getId());
        u.setAvatar(user.getAvatar());
        u.setExpires(new Date(System.currentTimeMillis() + tokenExpireMs));
        u.setPermissions(user.getRoleNameList());
        u.setRoles(user.getRoleNameList());
        u.setNickname(user.getNickName());
        return Result.success(u);
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        return Result.success(true);
    }

    @Override
    public Result<User> findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) return Result.fail("No such user");

//        Long id = user.getId();
//        user.setPermissionList(findPermissionByUserId(id));
//        user.setRoleNameList(findRolesByUserId(id));
        return Result.success(user);
    }

    @Override
    public Result<List<User>> findUserListByPage() {
        return null;
    }

    @Override
    public List<String> findPermissionByUserId(Long id) {
        return userMapper.findPermissionsByUserId(id);
    }

    @Override
    public List<String> findRolesByUserId(Long id) {
        return userMapper.findRolesByUserId(id);
    }


    @Override
    public Result<Boolean> saveUserRole(Long userId, List<Long> roleIds) {

        return Result.success(true);
    }

    @Override
    public <E> Result<E> register(String username, String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) return Result.fail("两次密码不一致");
        if (findByUsername(username) != null) return Result.fail("已经存在");

        return userMapper.insertUser(username, password)>0?
                Result.success():Result.fail("失败");
    }
}
