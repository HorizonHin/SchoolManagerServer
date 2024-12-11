package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AdminService {
    <E> Result<E> addUser(String username, String password, String email, String role);

    Result<List<String>> selectRole();

    Result<String> addRole(Map<String, String> addRoleData);

     List<User> getAllUsers() ;

    Result<Void> deleteRoleByName(String roleName);

    Result<List<String>> getRoleByUsername(String username);

    Result<Void> updateUserRole(String username, List<String> roleList);
}
