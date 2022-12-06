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
     * 商品卖家编号
     */
    private Integer goodsUserId;

    /**
     * 买家编号
     */
    private Integer userId;

    /**
     * 订单开始时间
     */
    private Date createTime;

    /**
     * 订单状态 0-未交付 1-已交付 2-已取消
     */
    private Integer status;

    /**
     * 订单完成时间
     */
    private Date completeTime;

    /**
     * 卖家评分（五星制）
     */
    private Double goodsUserScore;

    /**
     * 卖家评价
     */
    private String goodsUserEvaluation;

    /**
     * 买家评分（五星制）
     */
    private Double userScore;

    /**
     * 买家评价
     */
    private String userEvaluation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}