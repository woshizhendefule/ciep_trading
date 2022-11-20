package com.chengyu.ciep_trading.domain.vo;

import com.chengyu.ciep_trading.domain.User;

/**
 * User 脱敏
 *
 * @author CL
 */
public class UserInfo {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 手机
     */
    private String phone;

    /**
     * 用户信用评分
     */
    private Integer creditScore;

    /**
     * 用户类型 0-买家 1-卖家 2-待审核
     */
    private Integer isSeller;

    public UserInfo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.phone = user.getPhone();
        this.creditScore = user.getCreditScore();
        this.isSeller = user.getIsSeller();
    }


}
