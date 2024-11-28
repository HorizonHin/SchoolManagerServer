package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.pojos.Permission;
import com.schoolmanagerserver.pojos.PermissionQueryVo;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.RolePermissionVo;
import com.schoolmanagerserver.service.PermissionService;

import java.util.ArrayList;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public Result<List<Permission>> findParentPermissionList() {
        return null;
    }

    @Override
    public Result<List<Permission>> findPermissionList(PermissionQueryVo permissionQueryVo) {
        return null;
    }

    @Override
    public Result<List<Permission>> findPermissionListByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<RolePermissionVo> findPermissionTree(Long roleId, Long parentId) {
        return new Result<>(1, null , new RolePermissionVo());
    }

    @Override
    public Result<Boolean> hasChildrenOfPermission(Long permissionId) {
        return null;
    }
}
