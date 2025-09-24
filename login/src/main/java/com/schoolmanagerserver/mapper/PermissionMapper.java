package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<Permission> getAllPermissions();

    List<Permission> getPermissionsByRoleName(@Param("roleName") String roleName);

    void insertPermission(List<Permission> permission);

    void updatePermission(@Param("permission") Permission permission);

    // 调用存储过程进行软删除权限节点和真删除关联表数据
    void deletePermission(List<Long> allPermissionsToDelete);

    List<Long> findChildrenByParentId(Long parentId);

    void deleteRolePermissions(Long id);

    void softDeletePermission(Long id);

    void insertPermissionsForRole(@Param("roleName") String roleName, @Param("permissions") List<Long> permissions);
    // 删除角色的权限
    void deletePermissionsForRole(@Param("roleName") String roleName, @Param("permissions") List<Long> permissions);
}
