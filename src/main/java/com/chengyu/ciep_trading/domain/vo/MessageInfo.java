package com.chengyu.ciep_trading.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 留言用户信息（Message / userName / goodsName / fatherMessageName / atUserName）
 *
 * @author CL
 */
@Data
public class MessageInfo {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 商品编号 !0-父留言 0-子留言
     */
    private Integer goodsId;


    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 留言内容
     */
    private String details;

    /**
     * 留言时间
     */
    private Date createTime;

    /**
     * 父留言用户编号 0-父留言 !0-子留言
     */
    private Integer fatherMessageId;

    /**
     * at留言用户编号 0-父留言 !0-子留言
     */
    private Integer atUserId;

    /**
     * 父留言用户名
     */
    private String fatherMessageName;

    /**
     * at留言用户名
     */
    private String atUserName;

    public MessageInfo() {

    }

    public MessageInfo(Integer id, Integer userId, String userName, Integer goodsId, String goodsName, String details, Date createTime, Integer fatherMessageId, Integer atUserId, String fatherMessageName, String atUserName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.details = details;
        this.createTime = createTime;
        this.fatherMessageId = fatherMessageId;
        this.atUserId = atUserId;
        this.fatherMessageName = fatherMessageName;
        this.atUserName = atUserName;
    }
}
