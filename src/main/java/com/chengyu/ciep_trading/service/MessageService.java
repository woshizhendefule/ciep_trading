package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.Message;
import com.chengyu.ciep_trading.domain.vo.MessageInfo;

import java.util.List;

/**
 * @author CL
 * @description 针对表【message(留言表)】的数据库操作Service
 * @createDate 2022-12-06 18:56:41
 */
public interface MessageService extends IService<Message> {

    /**
     * 发布留言
     *
     * @param message 留言信息
     * @return boolean
     */
    boolean postMessage(Message message);

    /**
     * 显示父级留言
     *
     * @param goodsId 商品id
     * @return 留言用户信息列表
     */
    List<MessageInfo> getParentMessagesUser(Integer goodsId);

    /**
     * 显示父级留言：父级留言用户信息列表
     *
     * @param goodsId 商品id
     * @return 留言用户信息列表
     */
    List<MessageInfo> getParentMessagesUserJoinUser(Integer goodsId);

    /**
     * 显示子级留言
     *
     * @param goodsId         商品id
     * @param fatherMessageId 父留言编号
     * @return 留言用户信息列表
     */
    List<MessageInfo> getChildMessagesUser(Integer goodsId, Integer fatherMessageId);

    /**
     * 显示子级留言：子级留言用户信息列表
     *
     * @param goodsId         商品id
     * @param fatherMessageId 父留言编号
     * @return 留言用户信息列表
     */
    List<MessageInfo> getChildMessagesUserJoinUser(Integer goodsId, Integer fatherMessageId);

    /**
     * 显示父级 / 子级留言
     *
     * @return 留言用户信息列表
     */
    List<MessageInfo> getParentChildMessagesUser();

    /**
     * 显示父级 / 子级留言：父级 / 子级留言用户信息列表
     *
     * @return 留言用户信息列表
     */
    List<MessageInfo> getParentChildMessagesUserJoinUser();

    /**
     * 删除留言
     *
     * @param id 留言id
     * @return boolean
     */
    boolean deleteMessage(Integer id);
}
