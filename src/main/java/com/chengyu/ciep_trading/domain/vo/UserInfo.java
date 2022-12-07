package com.chengyu.ciep_trading.domain.vo;

import com.chengyu.ciep_trading.domain.User;
import lombok.Data;

/**
 * 用户脱敏评分信息（无password / 无isAdmin / goodsUserScore / userScore）
 *
 * @author CL
 */
@Data
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

    /**
     * 卖家评分（五星制）
     */
    private Double goodsUserScore;

    /**
     * 买家评分（五星制）
     */
    private Double userScore;

    public UserInfo() {

    }

    public UserInfo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.phone = user.getPhone();
        this.creditScore = user.getCreditScore();
        this.isSeller = user.getIsSeller();
    }
}
