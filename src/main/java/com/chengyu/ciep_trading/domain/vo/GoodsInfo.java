package com.chengyu.ciep_trading.domain.vo;

import com.chengyu.ciep_trading.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * 商品用户信息（Goods / userName / goodsUserScore / userScore）
 *
 * @author CL
 */
@Data
public class GoodsInfo {

    /**
     * 自增id
     */
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
     * 用户id
     */
    private Integer userId;

    /**
     * 商品状态 0-已发布 1-未发布 2-待审核 3-订单中 / 已交付
     */
    private Integer isRelease;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 卖家评分（五星制）
     */
    private Double goodsUserScore;

    /**
     * 买家评分（五星制）
     */
    private Double userScore;

    public GoodsInfo() {

    }

    public GoodsInfo(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.introduce = goods.getIntroduce();
        this.picture = goods.getPicture();
        this.credential = goods.getCredential();
        this.price = goods.getPrice();
        this.releaseTime = goods.getReleaseTime();
        this.userId = goods.getUserId();
        this.isRelease = goods.getIsRelease();
        this.userName = userName;
    }
}
