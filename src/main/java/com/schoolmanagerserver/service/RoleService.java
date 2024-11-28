package com.schoolmanagerserver.service;

import com.github.pagehelper.Page;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.Role;
import com.schoolmanagerserver.query.RoleQueryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    /**
     * 根据角色 ID 删除角色
     *
     * @param roleId 角色 ID
     * @return 是否删除成功
     */
    Result<Boolean> deleteRoleById(Long roleId);

    /**
     * 根据用户 ID 查找角色 ID 列表
     *
     * @param userId 用户 ID
     * @return 角色 ID 列表
     */
    Result <List<Long>> findRoleIdByUserId(Long userId);

    /**
     * 根据用户 ID 分页查询角色列表
     *
     * @param page      分页信息
     * @param roleQueryVo 查询条件
     * @return 角色分页列表
     */
    Result <Page<Role>> findRoleListByUserIdPage(Page<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 根据用户 ID 查找角色名称列表
     *
     * @param userId 用户 ID
     * @return 角色名称列表
     */
    Result<List<String>> findRoleNameByUserId(Long userId);

    /**
     * 判断用户是否有角色
     *
     * @param userId 用户 ID
     * @return 是否有角色
     */
    Result<Boolean> hasRoleCount(Long userId);

    /**
     * 保存角色权限
     *
     * @param roleId      角色 ID
     * @param permissionIds 权限 ID 列表
     * @return 是否保存成功
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);
}