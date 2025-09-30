package com.schoolmanagerserver.pojos;

import lombok.Data;

@Data
public class StudentInfoDto {
    private Long id;
    private String username;   // 用户名（学号）
    private String realName;   // 真实姓名
    private String nickName;   // 昵称
    private String email;      // 邮箱
    private String phone;      // 电话
    private String mobile;     // 手机
    private Integer gender;    // 性别（0=女，1=男）
    private String avatar;     // 头像
    private Boolean isEnabled; // 是否启用
}

