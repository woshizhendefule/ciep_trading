package com.chengyu.ciep_trading.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.domain.Message;
import com.chengyu.ciep_trading.mapper.MessageMapper;
import com.chengyu.ciep_trading.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author CL
 * @description 针对表【message(留言表)】的数据库操作Service实现
 * @createDate 2022-11-17 23:20:33
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

}




