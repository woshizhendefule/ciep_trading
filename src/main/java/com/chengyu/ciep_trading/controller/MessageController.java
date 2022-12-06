package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.Message;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.MessageInfo;
import com.chengyu.ciep_trading.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@Api(tags = "message(留言表) Controller")
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @Operation(summary = "发布留言")
    @PostMapping("/postMessage")
    public BaseResponse<Boolean> postMessage(User user, @RequestBody Message message) {
        message.setUserId(user.getId());
        return ResultUtils.success(messageService.postMessage(message));
    }

    @Operation(summary = "显示父级留言")
    @GetMapping("/getParentMessagesUser")
    public BaseResponse<List<MessageInfo>> getParentMessagesUser(@RequestParam("goodsId") Integer goodsId) {
        return ResultUtils.success(messageService.getParentMessagesUser(goodsId));
    }

    @Operation(summary = "显示子级留言")
    @GetMapping("/getChildMessagesUser")
    public BaseResponse<List<MessageInfo>> getChildMessagesUser(@RequestParam("goodsId") Integer goodsId,
                                                                @RequestParam("fatherMessageId") Integer fatherMessageId) {
        return ResultUtils.success(messageService.getChildMessagesUser(goodsId, fatherMessageId));
    }

    @Operation(summary = "显示父级 / 子级留言")
    @GetMapping("/getParentChildMessagesUser")
    public BaseResponse<List<MessageInfo>> getParentChildMessagesUser(@RequestParam("goodsId") Integer goodsId) {
        return ResultUtils.success(messageService.getParentChildMessagesUser(goodsId));
    }

    @Operation(summary = "删除留言")
    @PostMapping("/deleteMessage")
    public BaseResponse<Boolean> deleteMessage(@RequestParam("id") Integer id) {
        return ResultUtils.success(messageService.deleteMessage(id));
    }
}
