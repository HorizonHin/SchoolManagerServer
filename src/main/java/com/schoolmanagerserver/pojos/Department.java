package com.schoolmanagerserver.pojos;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Department implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String address;
    private List<Department> children = new ArrayList<>();
    private String departmentName;
    private Long id;
    private Integer isDelete;
    private Boolean open;
    private Integer orderNum;
    private String parentName;
    private String phone;
    private Long pid;

    // 省略getter和setter方法

    // ... 其他方法
}