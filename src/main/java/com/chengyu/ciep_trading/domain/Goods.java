package com.chengyu.ciep_trading.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 *
 * @author CL
 * @TableName goods
 */
@TableName(value = "goods")
@Data
public class Goods implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品介绍
     */
    private String introduce;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 商品凭证
     */
    private String credential;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 商品状态 0-已发布 1-未发布 2-待审核 3-订单中 / 已交付
     */
    private Integer isRelease;

    /**
     * 用户id
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}