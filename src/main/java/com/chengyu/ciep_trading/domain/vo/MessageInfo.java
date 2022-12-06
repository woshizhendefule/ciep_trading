package com.chengyu.ciep_trading.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 留言用户信息（Message / fatherMessageName / atUserName）
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
     * 商品编号 !0-父留言 0-子留言
     */
    private Integer goodsId;

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

    public MessageInfo(Integer id, Integer userId, Integer goodsId, String details, Date createTime, Integer fatherMessageId, Integer atUserId, String fatherMessageName, String atUserName) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.details = details;
        this.createTime = createTime;
        this.fatherMessageId = fatherMessageId;
        this.atUserId = atUserId;
        this.fatherMessageName = fatherMessageName;
        this.atUserName = atUserName;
    }
}
