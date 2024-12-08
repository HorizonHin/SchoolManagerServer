package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.PermissionMapper;
import com.schoolmanagerserver.pojos.Permission;
import com.schoolmanagerserver.pojos.PermissionQueryVo;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.RolePermissionVo;
import com.schoolmanagerserver.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAllPermissions() {
        List<Permission> allPermissions = permissionMapper.getAllPermissions();
        List<Permission> permissionTree = buildPermissionTree(allPermissions, null);
        return permissionTree;
    }

    @Override
    public List<Permission> getPermissionsByRoleName(String roleName) {
        List<Permission> permissionsByRoleName = permissionMapper.getPermissionsByRoleName(roleName);
        List<Permission> permissionTree = buildPermissionTree(permissionsByRoleName, null);
        return permissionTree;
    }

    @Transactional
    @Override
    public Result<Void> updateRolePermissions(String roleName, List<Long> permissionIds) {
        List<Permission> permissionsByRoleName = permissionMapper.getPermissionsByRoleName(roleName);
        Set<Long> oldSet = new HashSet<>();
        for (Permission permission : permissionsByRoleName) {
            oldSet.add(permission.getId());
        }

        List<Long> addedPermissions = new ArrayList<>();
        List<Long> removedPermissions = new ArrayList<>();


        Set<Long> newSet = new HashSet<>(permissionIds);
        for (Long id : permissionIds) {
            if (!oldSet.contains(id)) {
                addedPermissions.add(id);
            }
        }
        for (Long id : oldSet) {
            if (!newSet.contains(id)) {
                removedPermissions.add(id);
            }
        }

        try {
            if (!addedPermissions.isEmpty()) permissionMapper.insertPermissionsForRole(roleName, addedPermissions);
            if (!removedPermissions.isEmpty()) permissionMapper.deletePermissionsForRole(roleName, removedPermissions);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @Transactional
    @Override
    public Result<Void> insertRolePermissions(String roleName, List<Long> permissionIds){
        try {
            permissionMapper.insertPermissionsForRole(roleName, permissionIds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    @Override
    public Result<Permission> updatePermission(Permission permission) {
        // 更新更新时间
        permission.setUpdateTime(new Date(System.currentTimeMillis()));
        // 执行数据库更新
        try {
            permissionMapper.updatePermission(permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 返回结果
        return Result.success("权限修改成功", permission);
    }

    @Override
    public List<Permission> buildPermissionTree(List<Permission> allPermissions, Long parentId) {
        List<Permission> tree = new ArrayList<>();
        for (Permission permission : allPermissions) {
            if (permission.getParentId() == null && parentId == null || (permission.getParentId() != null && permission.getParentId().equals(parentId))) {
                List<Permission> children = buildPermissionTree(allPermissions, permission.getId());
                permission.setChildren(children);
                tree.add(permission);
            }
        }
        return tree;
    }

    @Override
    @Transactional
    public Result<Void> addPermissions(List<Permission> newPermissionData) {
        for (Permission permission : newPermissionData) {
            // 为每个权限设置默认值
            if (permission.getCreateTime() == null) {
                permission.setCreateTime(new java.sql.Date(System.currentTimeMillis())); // 设置创建时间为当前时间
            }
            if (permission.getUpdateTime() == null) {
                permission.setUpdateTime(new java.sql.Date(System.currentTimeMillis())); // 设置更新时间为当前时间
            }
            if (permission.getIsDelete() == null) {
                permission.setIsDelete(0); // 默认未删除
            }
            if (permission.getOpen() == null) {
                permission.setOpen(true); // 默认打开
            }

            // 确保 value 字段不为空，必须在数据库中唯一
            if (permission.getValue() == null || permission.getValue().isEmpty()) {
                return Result.fail("缺少value");
            }
            if (permission.getCode() == null || permission.getCode().isEmpty()) {
                return Result.fail("缺少code");
            }
        }
        // 执行插入操作
        try {
            permissionMapper.insertPermission(newPermissionData);
        } catch (Exception e) {
            log.error("发生错误: {}",e.getMessage());
           return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @Transactional
    @Override
    public void deletePermission(Long permissionId) {
        // 获取当前权限的所有子节点
        List<Long> allPermissionsToDelete = new ArrayList<>();
         allPermissionsToDelete.add(permissionId);
         allPermissionsToDelete.addAll(getAllChildren(permissionId));
        // 调用存储过程进行软删除权限节点和真删除关联表数据
            for (Long id : allPermissionsToDelete) {
                permissionMapper.deleteRolePermissions(id);
                permissionMapper.softDeletePermission(id);
            }

    }

    @Override
    public List<Long> getAllChildren(Long parentId) {
        List<Long> childrenIds = permissionMapper.findChildrenByParentId(parentId);
        List<Long> allChildrenIds = new ArrayList<>(childrenIds);

        for (Long childId : childrenIds) {
            allChildrenIds.addAll(getAllChildren(childId));  // 递归查找子节点
        }

        return allChildrenIds;
    }

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
        return Result.success(new RolePermissionVo());
    }

    @Override
    public Result<Boolean> hasChildrenOfPermission(Long permissionId) {
        return null;
    }
}
