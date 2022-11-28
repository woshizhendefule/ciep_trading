package com.chengyu.ciep_trading.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表
 *
 * @author CL
 * @TableName goods_order
 */
@TableName(value = "goods_order")
@Data
public class GoodsOrder implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单状态 0-未交付 1-已交付
     */
    private Integer status;

    /**
     * 订单开始时间
     */
    private Date createTime;

    /**
     * 订单完成时间
     */
    private Date completeTime;

    /**
     * 订单完成卖家评分（百分制）
     */
    private Integer sellerScore;

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