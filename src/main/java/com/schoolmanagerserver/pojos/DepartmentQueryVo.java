package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DepartmentQueryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String departmentName;  // 部门名称，用于模糊查询
    private Long parentId;  // 上级部门ID，用于查询某个部门下的所有子部门
    private Boolean isDelete;  // 是否已删除，用于过滤删除的部门
    private Integer open;  // 是否启用，用于过滤启用或禁用的部门
    private Integer orderNum;  // 排序字段，用于按某个字段排序
    private String address;  // 地址信息，用于查询部门所在的地址

    private Integer page = 1;  // 当前页码，默认为1
    private Integer size = 10;  // 每页显示条数，默认为10
}
