package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.UserInfo;

import java.util.List;

/**
 * @author CL
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2022-11-18 20:15:41
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return boolean
     */
    boolean login(String name, String password);

    /**
     * 用户注册
     *
     * @param name      用户名
     * @param password  密码
     * @param studentId 学号
     * @param phone     手机
     * @return boolean
     */
    boolean registered(String name, String password, String studentId, String phone);

    /**
     * 找回密码
     *
     * @param name        用户名
     * @param studentId   学号
     * @param phone       手机
     * @param newPassword (新)密码
     * @return boolean
     */
    boolean retrievePassword(String name, String studentId, String phone, String newPassword);

    /**
     * 修改密码
     *
     * @param name        用户名
     * @param oldPassword (旧)密码
     * @param newPassword (新)密码
     * @return boolean
     */
    boolean changePassword(String name, String oldPassword, String newPassword);

    /**
     * 用户个人信息查看
     *
     * @return UserInfo - User 脱敏
     */
    UserInfo toViewUserInfo();

    /**
     * 用户个人信息修改（name / phone）
     *
     * @param user 用户User数据
     * @return boolean
     */
    boolean modifyUser(User user);

    /**
     * 卖家资质申请
     *
     * @param
     * @return boolean
     */
    boolean sellerQualificationApply();

    /**
     * 显示所有用户
     *
     * @param
     * @return
     */
    List<UserInfo> getAllUser();

    /**
     * 删除指定用户
     *
     * @return boolean
     */
    boolean deleteUser();

}