package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.Permission;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.User;
import com.schoolmanagerserver.service.AdminService;
import com.schoolmanagerserver.service.PermissionService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    PermissionService permissionService;

    public static final Logger logger  = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/addUser")
    public <E>Result<E> addUser(@RequestBody Map<String, String> addUserData){
        logger.info("执行了addUser: {}",addUserData.toString());
        return adminService.addUser(addUserData.get("username"),addUserData.get("password"),
                addUserData.get("email"),addUserData.get("role"));
    }

    @GetMapping("/selectRole")
    public Result<List<String>> selectRole(){
        return adminService.selectRole();
    }

    @GetMapping("/roleOfUser")
    public Result<List<String>> roleOfUser(@RequestParam String username){
        return adminService.getRoleByUsername(username);
    }

    @PutMapping("/updateUserRole")
    public Result<Void> updateUserRole(@RequestBody Map<String, String> updateUserRoleData){
        String username;
        String role;
        List<String> roleList;
        try {
            logger.info("新的用户角色{}", updateUserRoleData.toString());
             username = updateUserRoleData.get("username");
             role = updateUserRoleData.get("newRole");
             roleList = Arrays.stream(role.split(",")).filter(str -> !str.isBlank()).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return adminService.updateUserRole(username,roleList);
    }

    @PostMapping("/addRole")
    public Result<String> addRole(@RequestBody Map<String, String> addRoleData){
        return adminService.addRole(addRoleData);
    }

    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        if (users.isEmpty()) {
            return Result.fail("没有找到用户");
        }
        return Result.success(users);
    }

    @GetMapping("/permissions")
    public Result<List<Permission>> getPermissions(@RequestParam(value = "roleName",required = false) String roleName) {
        if (roleName == null) {
            List<Permission> allPermissions = permissionService.getAllPermissions();
            return Result.success(allPermissions);
        }
        List<Permission> permissions = permissionService.getPermissionsByRoleName(roleName);
        return Result.success(permissions);
    }

    @PostMapping("/addPermissions")
    public Result<String> addPermissions(@RequestBody List<Permission> newPermissionData){
        try {
            permissionService.addPermissions(newPermissionData);
            return Result.success("权限批量新增成功");
        } catch (Exception e) {
            return Result.fail("权限批量新增失败: " + e.getMessage());
        }
    }

    @PutMapping("/updatePermission")
    public Result<Permission> updatePermission(@RequestBody Permission permission) {
        // 调用 Service 层更新权限
        return permissionService.updatePermission(permission);
    }

    @PostMapping("updRolePerm")
    public Result<String> updRolePerm(@RequestBody Map<String, String> updRolePermData){
        try {
            String roleName = updRolePermData.get("roleName");
            String permIds = updRolePermData.get("permIds");
            List<Long> permList = Stream.of(permIds.split(",")).
                    map(Long::parseLong).toList();
            permissionService.updateRolePermissions(roleName, permList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    @DeleteMapping("/deletePermission")
    public Result<Void> deletePermission(@RequestBody Map<String, List<Long>> requestData) {
        List<Long> ids = requestData.get("ids");
        if (ids != null && !ids.isEmpty()) {
            try {
                for (Long id : ids) {
                    permissionService.deletePermission(id);
                }
                return Result.success("删除权限成功");
            } catch (Exception e) {
                return Result.fail("删除权限失败");
            }
        } else {
            return Result.fail("权限ID不能为空");
        }
    }

    @DeleteMapping("/deleteRole")
    public Result<Void> deleteRole(@RequestBody Map<String, String> requestData) {
        String roleName = requestData.get("roleName");
        if (roleName == null||roleName.isEmpty()) {
            return Result.fail("没有传入角色名");
        }

        if (roleName.equals("admin")) {
            return Result.fail("admin不可修改");
        }

        return adminService.deleteRoleByName(roleName);
    }
}
