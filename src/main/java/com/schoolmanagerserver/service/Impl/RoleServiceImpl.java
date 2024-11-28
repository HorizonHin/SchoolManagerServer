package com.schoolmanagerserver.service.Impl;

import com.github.pagehelper.Page;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.Role;
import com.schoolmanagerserver.query.RoleQueryVo;
import com.schoolmanagerserver.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public Result<Boolean> deleteRoleById(Long roleId) {
        return null;
    }

    @Override
    public Result<List<Long>> findRoleIdByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<Page<Role>> findRoleListByUserIdPage(Page<Role> page, RoleQueryVo roleQueryVo) {
        return null;
    }

    @Override
    public Result<List<String>> findRoleNameByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<Boolean> hasRoleCount(Long userId) {
        return null;
    }

    @Override
    public boolean saveRolePermission(Long roleId, List<Long> permissionIds) {
        return false;
    }
}
