package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.UserInfo;
import com.chengyu.ciep_trading.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@Api(tags = "user(用户表) Controller")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户登录")
    @GetMapping("/userLogin")
    public BaseResponse<String> userLogin(@RequestParam("name") String name, @RequestParam("password") String password) {
        return ResultUtils.success(userService.userLogin(name, password));
    }

    @Operation(summary = "管理员登录")
    @GetMapping("/adminLogin")
    public BaseResponse<String> adminLogin(@RequestParam("name") String name, @RequestParam("password") String password) {
        return ResultUtils.success(userService.adminLogin(name, password));
    }

    @Operation(summary = "用户注册")
    @PostMapping("/registered")
    public BaseResponse<Boolean> registered(@RequestBody User user) {
        return ResultUtils.success(userService.registered(user.getName(), user.getPassword(), user.getStudentId(), user.getPhone()));
    }

    @Operation(summary = "找回密码")
    @PostMapping("/retrievePassword")
    public BaseResponse<Boolean> retrievePassword(@RequestBody User user) {
        return ResultUtils.success(userService.retrievePassword(user.getName(), user.getStudentId(), user.getPhone(), user.getPassword()));
    }

    @Operation(summary = "修改密码")
    @PostMapping("/changePassword")
    public BaseResponse<Boolean> changePassword(User user, @RequestParam("newPassword") String newPassword) {
        return ResultUtils.success(userService.changePassword(user.getId(), newPassword));
    }

    @Operation(summary = "用户个人信息查看")
    @GetMapping("/toViewUserInfo")
    public BaseResponse<UserInfo> toViewUserInfo(UserInfo userInfo) {
        return ResultUtils.success(userService.toViewUserInfo(userInfo.getId()));
    }

    @Operation(summary = "用户个人信息修改（name）")
    @PostMapping("/modifyUserName")
    public BaseResponse<Boolean> modifyUserName(User user, @RequestParam("newName") String newName) {
        return ResultUtils.success(userService.modifyUserName(user.getId(), newName));
    }

    @Operation(summary = "用户个人信息修改（phone）")
    @PostMapping("/modifyUserPhone")
    public BaseResponse<Boolean> modifyUserPhone(User user, @RequestParam("newPhone") String newPhone) {
        return ResultUtils.success(userService.modifyUserPhone(user.getId(), newPhone));
    }

    @Operation(summary = "卖家资质申请")
    @GetMapping("/sellerQualificationApply")
    public BaseResponse<Boolean> sellerQualificationApply(User user) {
        return ResultUtils.success(userService.sellerQualificationApply(user.getId()));
    }

    @Operation(summary = "显示所有用户")
    @GetMapping("/getAllUser")
    public BaseResponse<List<UserInfo>> getAllUser() {
        return ResultUtils.success(userService.getAllUser());
    }

    @Operation(summary = "删除指定用户")
    @PostMapping("/deleteUser")
    public BaseResponse<Boolean> deleteUser(@RequestParam("id") Integer id) {
        return ResultUtils.success(userService.deleteUser(id));
    }

    @Operation(summary = "卖家资质审核通过")
    @PostMapping("/sellerQualificationCheckPass")
    public BaseResponse<Boolean> sellerQualificationCheckPass(@RequestParam("id") Integer id) {
        return ResultUtils.success(userService.sellerQualificationCheckPass(id));
    }

    @Operation(summary = "卖家资质审核未通过")
    @PostMapping("/sellerQualificationCheckNotPass")
    public BaseResponse<Boolean> sellerQualificationCheckNotPass(@RequestParam("id") Integer id) {
        return ResultUtils.success(userService.sellerQualificationCheckNotPass(id));
    }
}