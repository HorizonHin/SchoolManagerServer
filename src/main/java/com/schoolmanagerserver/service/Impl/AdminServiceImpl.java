package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.AdminMapper;
import com.schoolmanagerserver.mapper.UserMapper;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import com.schoolmanagerserver.service.AdminService;
import com.schoolmanagerserver.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    public static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public <E> Result<E> addUser(String username, String password, String email, String role) {
        Integer status = 0;
        password = MD5Util.getMD5(password);
        int res = adminMapper.addUser(username, password, email, role, status);
        if (res > 0) return Result.success();
        return Result.fail("加入失败");
    }

    @Override
    public Result<List<String>> selectRole() {
        List<String> roleList;
        try {
            roleList = adminMapper.selectRole();
            logger.info("查询到的角色列表： {}", roleList.toString());
            logger.debug("debug{}", roleList);
        } catch (Exception e) {
            logger.error("查询角色失败: {}", e.getMessage());
            return Result.fail("查询角色失败");
        }
        return Result.success(roleList);
    }

    @Override
    public Result<String> addRole(Map<String, String> addRoleData) {
        String roleName = addRoleData.get("newRoleName");
        if (roleName == null || roleName.trim().isEmpty()) {
            return Result.fail("请输入新增角色");
        }
        int res = 0;
        try {
            res = adminMapper.addRole(roleName);
        } catch (Exception e) {
            logger.warn("新增角色失败");
            return Result.fail(e.getMessage());
        }
        return res > 0 ? Result.success("成功") : Result.fail("失败");
    }

    @Override
    public List<User> getAllUsers() {
        return adminMapper.findAllUsers();
    }

    @Override
    public Result<Void> deleteRoleByName(String roleName) {
        try {
            adminMapper.deleteRoleByName(roleName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    @Override
    public Result<List<String>> getRoleByUsername(String username) {
        List<String> roles;
        try {
            roles = adminMapper.getRoleByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(roles);
    }

    @Transactional
    @Override
    public Result<Void> updateUserRole(String username, List<String> roleList) {
        Set<String> oldRole = new HashSet<>(getRoleByUsername(username).data);
        Set<String> newRole = new HashSet<>(roleList);

        logger.info("用户名{}", username);
        logger.info("旧的角色{}", oldRole);
        logger.info("新的角色{}", newRole);
        List<String> toAdd = new ArrayList<>();
        List<String> toRemove = new ArrayList<>();

        for (String role : oldRole) {
            if (!newRole.contains(role)) {
                toRemove.add(role);
            }
        }

        for (String role : newRole) {
            if (!oldRole.contains(role)) {
                toAdd.add(role);
            }
        }


        logger.info("准备新增{}", toAdd);
        logger.info("准备删除{}", toRemove);

        for (String role : toAdd) {
            logger.info("要添加的role ："+role);
        }

        if (!toAdd.isEmpty()) {
            adminMapper.addUserRole(username, toAdd);
        }
        if (!toRemove.isEmpty()) adminMapper.deleteUserRole(username, toRemove);


        return Result.success();
    }
}
