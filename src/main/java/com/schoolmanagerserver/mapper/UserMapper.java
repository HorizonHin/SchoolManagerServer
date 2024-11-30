package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.Permission;
import com.schoolmanagerserver.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    // 查询用户的角色名称
    List<String> findRolesByUserId(@Param("userId") Long userId);
    // 查询用户的权限
    List<String> findPermissionsByUserId(@Param("userId") Long userId);

    int insertUser(String username, String password);

    List<String> findRolesByUsername(String username);
    List<String> findPermissionByRoles(List<String> roles);
}
