package com.chengyu.ciep_trading.domain.vo;

import lombok.Data;

/**
 * 收藏商品用户（goodsName / introduce / picture / price / userName / phone）
 *
 * @author CL
 */
@Data
public class CollectionInfo {
    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品介绍
     */
    private String goodsIntroduce;

    /**
     * 商品图片
     */
    private String goodsPicture;

    /**
     * 商品价格
     */
    private Double goodsPrice;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机
     */
    private String userPhone;

    public CollectionInfo() {

    }

    public CollectionInfo(Integer goodsId, String goodsName, String goodsIntroduce, String goodsPicture, Double goodsPrice, Integer userId, String userName, String userPhone) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsIntroduce = goodsIntroduce;
        this.goodsPicture = goodsPicture;
        this.goodsPrice = goodsPrice;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
    }
}
