package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.service.MessageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CL
 */
@RestController
@Api(tags = "message(留言表) Controller")
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;
}
