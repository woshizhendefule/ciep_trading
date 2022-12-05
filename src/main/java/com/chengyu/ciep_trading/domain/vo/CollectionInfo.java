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
    private byte[] goodsPicture;

    /**
     * 商品价格
     */
    private Double goodsPrice;

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

    public CollectionInfo(String goodsName, String goodsIntroduce, byte[] goodsPicture, Double goodsPrice, String userName, String userPhone) {
        this.goodsName = goodsName;
        this.goodsIntroduce = goodsIntroduce;
        this.goodsPicture = goodsPicture;
        this.goodsPrice = goodsPrice;
        this.userName = userName;
        this.userPhone = userPhone;
    }
}
