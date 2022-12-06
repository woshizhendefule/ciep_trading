package com.chengyu.ciep_trading.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言表
 *
 * @author CL
 * @TableName message
 */
@TableName(value = "message")
@Data
public class Message implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 留言内容
     */
    private String details;

    /**
     * 留言时间
     */
    private Date createTime;

    /**
     * 商品卖家编号
     */
    private Integer goodsUserId;

    /**
     * 买家编号
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}