package com.chengyu.ciep_trading.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author CL
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

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
     * 管理员用户类型 0-用户 1-管理员
     */
    private Integer isAdmin;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}