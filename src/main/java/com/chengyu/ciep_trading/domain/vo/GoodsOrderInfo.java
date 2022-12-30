package com.chengyu.ciep_trading.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 订单用户商品信息（无goodsId / 无userId / goodsUserName / userName / goodsName）
 *
 * @author CL
 */
@Data
public class GoodsOrderInfo {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 订单开始时间
     */
    private Date createTime;

    /**
     * 订单状态 0-未交付 1-已交付 2-已取消 3-待取消
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

    /**
     * 卖家用户名
     */
    private String goodsUserName;

    /**
     * 卖家手机
     */
    private String goodsUserPhone;

    /**
     * 买家用户名
     */
    private String userName;

    /**
     * 买家手机
     */
    private String userPhone;

    /**
     * 商品名称
     */
    private String goodsName;

    public GoodsOrderInfo() {

    }

    public GoodsOrderInfo(Integer id, Date createTime, Integer status, Date completeTime, Double goodsUserScore, String goodsUserEvaluation, Double userScore, String userEvaluation, String goodsUserName, String goodsUserPhone, String userName, String userPhone, String goodsName) {
        this.id = id;
        this.createTime = createTime;
        this.status = status;
        this.completeTime = completeTime;
        this.goodsUserScore = goodsUserScore;
        this.goodsUserEvaluation = goodsUserEvaluation;
        this.userScore = userScore;
        this.userEvaluation = userEvaluation;
        this.goodsUserName = goodsUserName;
        this.goodsUserPhone = goodsUserPhone;
        this.userName = userName;
        this.userPhone = userPhone;
        this.goodsName = goodsName;
    }
}
