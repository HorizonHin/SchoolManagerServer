package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.AdminMapper;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import com.schoolmanagerserver.service.AdminService;
import com.schoolmanagerserver.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    public static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;

    @Override
    public <E> Result<E> addUser(String username, String password, String email, String role) {
        Integer status = 0;
        password = MD5Util.getMD5(password);
        int res = adminMapper.addUser(username, password, email, role,status);
        if (res > 0) return Result.success();
        return Result.fail("加入失败");
    }

    @Override
    public Result<List<String>> selectRole() {
        List<String> roleList;
        try {
            roleList = adminMapper.selectRole();
            logger.info("查询到的角色列表： {}",roleList.toString());
            logger.debug("debug{}",roleList);
        } catch (Exception e) {
            logger.error("查询角色失败: {}",e.getMessage());
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
}
