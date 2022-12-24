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
     * @return Token(String)
     */
    String userLogin(String name, String password);

    /**
     * 管理员登录
     *
     * @param name     用户名
     * @param password 密码
     * @return Token(String)
     */
    String adminLogin(String name, String password);


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
     * @param id          用户id
     * @param newPassword (新)密码
     * @return boolean
     */
    boolean changePassword(Integer id, String newPassword);

    /**
     * 用户个人信息查看
     *
     * @param id 用户id
     * @return UserInfo - User 脱敏
     */
    UserInfo toViewUserInfo(Integer id);

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
     * @param id 用户id
     * @return boolean
     */
    boolean sellerQualificationApply(Integer id);

    /**
     * 显示所有用户
     *
     * @return 脱敏用户列表
     */
    List<UserInfo> getAllUser();

    /**
     * 显示所有用户：卖家用户评分信息
     *
     * @param id 用户id
     * @return 用户脱敏评分信息
     */
    Double getAvgGoodsUserScoreJoinGoodsGoodsOrder(Integer id);

    /**
     * 显示所有用户：买家用户评分信息
     *
     * @param id 用户id
     * @return 用户脱敏评分信息
     */
    Double getAvgUserScoreJoinGoodsGoodsOrder(Integer id);

    /**
     * 删除指定用户
     *
     * @param id 用户id
     * @return boolean
     */
    boolean deleteUser(Integer id);

    /**
     * 卖家资质审核通过
     *
     * @param id 用户id
     * @return boolean
     */
    boolean sellerQualificationCheckPass(Integer id);

    /**
     * 卖家资质审核未通过
     *
     * @param id 用户id
     * @return boolean
     */
    boolean sellerQualificationCheckNotPass(Integer id);
}