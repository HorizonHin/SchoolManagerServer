package com.schoolmanagerserver.pojos;

import java.io.Serial;
import java.util.Date;
import java.util.List;
import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String avatar;   //用户头像地址
    private String email;
    private Integer gender;
    private Long id;
    private String nickName;
    private String password;
    private List<String> permissionList;
    private String phone;
    private String realName;
    private String mobile;
    private String username;

    private Date createTime;
    private Long departmentId;
    private String departmentName;

    private boolean isAccountExpired = false;
    private boolean isAccountLocked = false;
    private boolean isAdmin = false;
    private boolean isCredentialsExpired = false;
    private boolean isDelete;
    private boolean isEnabled = true;

    private List<String> roleNameList;
    private Date updateTime;
}