package com.shopping.controller;

import com.shopping.commons.Result;
import com.shopping.model.UserInfo;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody UserInfo userInfo){
        try {
            userService.register(userInfo);
            return Result.success("注册成功");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Result.fail(500,"注册失败");
    }
}
