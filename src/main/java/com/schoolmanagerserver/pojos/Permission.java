package com.schoolmanagerserver.pojos;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    // 子权限列表
    private List<Permission> children = new ArrayList<>();
    // 权限代码
    private String code;
    // 创建时间
    private Date createTime;
    // 图标
    private String icon;
    // 权限ID
    private Long id;
    // 是否删除
    private Integer isDelete;
    // 权限标签
    private String label;
    // 是否展开
    private Boolean open;
    // 排序编号
    private Integer orderNum;
    // 父权限ID
    private Long parentId;
    // 父权限名称
    private String parentName;
    // 权限路径
    private String path;
    // 备注
    private String remark;
    // 权限类型
    private Integer type;
    // 更新时间
    private Date updateTime;
    // 权限URL
    private String url;
    // 权限值
    private String value;
}