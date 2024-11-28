package com.schoolmanagerserver.pojos;

import lombok.Data;

@Data
public class PermissionQueryVo {
    // 其他需要的条件字段
    // 权限名称，支持模糊查询
    private String permissionName;
    // 状态字段（启用、禁用等）
    private String status;
    // 角色 ID，筛选角色的权限
    private Long roleId;

}
