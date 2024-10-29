package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String avatar;
    private Long id;
    private String introduction;
    private String name;
    private List<String> roleName;
    private Object[] roles;

}