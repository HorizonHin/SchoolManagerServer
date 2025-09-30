package com.schoolmanagerserver.query;

import lombok.Data;

@Data
public class StudentQueryCondition {
    private String username;   // 用户名（学号）
    private String realName;   // 真实姓名
    private String email;      // 邮箱
    private Integer gender;    // 性别
    private Boolean isEnabled; // 是否启用
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}

