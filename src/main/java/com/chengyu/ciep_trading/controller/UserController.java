package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.UserInfo;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/changePassword")
    public BaseResponse<Boolean> changePassword(User user, @RequestParam("newPassword") String newPassword) {
        return ResultUtils.success(userService.changePassword(user.getId(), newPassword));
    }

    @GetMapping("/toViewUserInfo")
    public BaseResponse<UserInfo> toViewUserInfo(UserInfo userInfo) {
        return ResultUtils.success(userService.toViewUserInfo(userInfo.getId()));
    }

    @PostMapping("/modifyUser")
    public BaseResponse<Boolean> modifyUser(@RequestBody User user) {
        return ResultUtils.success(userService.modifyUser(user));
    }

    @PostMapping("/sellerQualificationApply")
    public BaseResponse<Boolean> sellerQualificationApply(User user) {
        return ResultUtils.success(userService.sellerQualificationApply(user.getId()));
    }

    @GetMapping("/getAllUser")
    public BaseResponse<List<UserInfo>> getAllUser() {
        return ResultUtils.success(userService.getAllUser());
    }

    @PostMapping("/deleteUser")
    public BaseResponse<Boolean> deleteUser(User user) {
        return ResultUtils.success(userService.deleteUser(user.getId()));
    }

    @PostMapping("/sellerQualificationCheckPass")
    public BaseResponse<Boolean> sellerQualificationCheckPass(User user) {
        return ResultUtils.success(userService.sellerQualificationCheckPass(user.getId()));
    }

    @PostMapping("/sellerQualificationCheckNotPass")
    public BaseResponse<Boolean> sellerQualificationCheckNotPass(User user) {
        return ResultUtils.success(userService.sellerQualificationCheckNotPass(user.getId()));
    }
}