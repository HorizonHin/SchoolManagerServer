package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    int addUser(@Param("username") String username,
                @Param("password") String password,
                @Param("email") String email,
                @Param("role") String role,@Param("status") Integer status);

    List<String> selectRole();

    int addRole(String newRoleName);

    List<User> findAllUsers();
}
