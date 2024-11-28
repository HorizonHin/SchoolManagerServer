package com.schoolmanagerserver.query;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginQuery {

    private Long id; // 用户ID
    private String avatar; // 用户头像地址
    private String username; // 用户名
    private String nickname; // 用户昵称
    private List<String> roles; // 当前登录用户的角色列表
    private List<String> permissions; // 按钮级别权限列表
    private String accessToken; // 用户的访问Token
    private String refreshToken; // 用于刷新Token的Token
    private Date expires; // accessToken的过期时间

}