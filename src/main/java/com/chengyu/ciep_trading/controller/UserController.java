package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author CL
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public BaseResponse<String> login(@RequestParam("name") String name, @RequestParam("password") String password) {
        return ResultUtils.success(userService.login(name, password));
    }

    @PostMapping("/registered")
    public BaseResponse<Boolean> registered(@RequestBody User user) {
        return ResultUtils.success(userService.registered(user.getName(), user.getPassword(), user.getStudentId(), user.getPhone()));
    }

    @PostMapping("/retrievePassword")
    public BaseResponse<Boolean> retrievePassword(@RequestBody User user) {
        return ResultUtils.success(userService.retrievePassword(user.getName(), user.getStudentId(), user.getPhone(), user.getPassword()));
    }
}