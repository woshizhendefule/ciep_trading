package com.chengyu.ciep_trading.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 留言表
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}