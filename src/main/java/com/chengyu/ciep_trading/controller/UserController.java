package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(@RequestParam("name") String name, @RequestParam("password") String password) {
        return userService.login(name, password);
    }

}
