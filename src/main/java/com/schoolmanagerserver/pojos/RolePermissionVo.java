package com.schoolmanagerserver.pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RolePermissionVo {
    // 其他需要的字段和方法
    // 角色 ID
    private Long roleId;
    // 权限节点列表（可以是子节点的递归嵌套结构）
    private List<Permission> permissions;
    // 是否有访问全部权限的标志
    private boolean hasFullAccess;
}
