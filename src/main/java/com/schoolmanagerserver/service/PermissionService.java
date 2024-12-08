package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Permission;
import com.schoolmanagerserver.pojos.PermissionQueryVo;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.RolePermissionVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface PermissionService {

    @Transactional
    void deletePermission(Long permissionId);

    public List<Long> getAllChildren(Long parentId);

    /**
     * 查找父权限列表
     * @return 父权限列表
     */
    Result<List<Permission>> findParentPermissionList();

    /**
     * 根据查询条件查找权限列表
     * @param permissionQueryVo 查询条件
     * @return 权限列表
     */
    Result<List<Permission>> findPermissionList(PermissionQueryVo permissionQueryVo);

    /**
     * 根据用户ID查找权限列表
     * @param userId 用户ID
     * @return 权限列表
     */
    Result<List<Permission>> findPermissionListByUserId(Long userId);

    /**
     * 查找权限树
     * @param roleId 角色ID
     * @param parentId 父节点ID
     * @return 角色权限视图
     */
    Result<RolePermissionVo> findPermissionTree(Long roleId, Long parentId);

    /**
     * 判断权限是否有子节点
     * @param permissionId 权限ID
     * @return 是否有子节点
     */
    Result<Boolean> hasChildrenOfPermission(Long permissionId);

    List<Permission> getAllPermissions();
    List<Permission>  buildPermissionTree(List<Permission> permissionList,Long parentId );

    Result<Void> addPermissions(List<Permission> newPermissionData);

    List<Permission> getPermissionsByRoleName(String roleName);

    @Transactional
    Result<Void> updateRolePermissions(String roleName, List<Long> permissionIds);

    @Transactional
    Result<Void> insertRolePermissions(String roleName, List<Long> permissionIds);

    Result<Permission> updatePermission(Permission permission);
}