package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import com.schoolmanagerserver.query.UserLoginQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Result<UserLoginQuery> login(String username, String password);

    Result<Boolean> deleteById(Long id);

    Result<User> findByUsername(String username);

    List<String> findRolesByUsername(String username);

    List<String> findPermissionByRoles(List<String> roles);

    //根据页码来找UserList，后续自己写
    Result<List<User>> findUserListByPage();

    List<String> findPermissionByUserId(Long id);

    List<String> findRolesByUserId(Long id);

    Result<Boolean> saveUserRole(Long userId, List<Long> roleIds);

    <E> Result<E> register(String username, String password, String repeatPassword);

    <E>Result<E> addUser(String username, String password, String email, String role);
}