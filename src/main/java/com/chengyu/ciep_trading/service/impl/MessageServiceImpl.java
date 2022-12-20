package com.chengyu.ciep_trading.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.domain.Message;
import com.chengyu.ciep_trading.domain.vo.MessageInfo;
import com.chengyu.ciep_trading.exception.BusinessException;
import com.chengyu.ciep_trading.mapper.MessageMapper;
import com.chengyu.ciep_trading.service.GoodsService;
import com.chengyu.ciep_trading.service.MessageService;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 * @description 针对表【message(留言表)】的数据库操作Service实现
 * @createDate 2022-12-06 18:56:41
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private UserService userService;

    @Resource
    private MessageMapper messageMapper;

    @Override
    public boolean postMessage(Message message) {
        // 校验
        if (StrUtil.hasBlank(message.getDetails())) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (goodsService.getById(message.getGoodsId()) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该商品");
        }

        // 判断父留言编号是否有效
        if (message.getFatherMessageId() != null && this.getById(message.getFatherMessageId()) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该留言");
        }

        // 判断at留言用户编号是否有效
        if (message.getAtUserId() != null && userService.getById(message.getAtUserId()) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该用户");
        }

        // 插入留言表
        return this.save(message);
    }

    @Override
    public List<MessageInfo> getParentMessagesUser(Integer goodsId) {
        // 查询到所有父级留言
        return this.getParentMessagesUserJoinUser(goodsId);
    }

    @Override
    public List<MessageInfo> getParentMessagesUserJoinUser(Integer goodsId) {
        return messageMapper.getParentMessagesUserJoinUser(goodsId);
    }

    @Override
    public List<MessageInfo> getChildMessagesUser(Integer goodsId, Integer fatherMessageId) {
        // 查询到所有子级留言
        return this.getChildMessagesUserJoinUser(goodsId, fatherMessageId);
    }

    @Override
    public List<MessageInfo> getChildMessagesUserJoinUser(Integer goodsId, Integer fatherMessageId) {
        return messageMapper.getChildMessagesUserJoinUser(goodsId, fatherMessageId);
    }

    @Override
    public List<MessageInfo> getParentChildMessagesUser() {
        // 查询到所有父级 / 子级留言
        return this.getParentChildMessagesUserJoinUser();
    }

    @Override
    public List<MessageInfo> getParentChildMessagesUserJoinUser() {
        return messageMapper.getParentChildMessagesUserJoinUser();
    }

    @Override
    public boolean deleteMessage(Integer id) {
        // 删除留言
        return this.removeById(id);
    }
}




