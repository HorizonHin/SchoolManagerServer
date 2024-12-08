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

import java.util.List;
import java.util.Map;
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
}
