package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    UserService userService;

    public static final Logger logger  = LoggerFactory.getLogger(adminController.class);

    @PostMapping("/addUser")
    public <E>Result<E> addUser(@RequestBody Map<String, String> addUserData){
        logger.info("执行了addUser: {}",addUserData.toString());
        return userService.addUser(addUserData.get("username"),addUserData.get("password"),
                addUserData.get("email"),addUserData.get("role"));
    }
}
