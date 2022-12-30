package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.GoodsOrder;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.GoodsOrderInfo;
import com.chengyu.ciep_trading.service.GoodsOrderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@Api(tags = "goods_order(订单表) Controller")
@RequestMapping("/goods_order")
public class GoodsOrderController {

    @Resource
    private GoodsOrderService goodsOrderService;

    @Operation(summary = "创建订单")
    @PostMapping("/createOrders")
    public BaseResponse<Boolean> createOrders(User user, @RequestBody GoodsOrder goodsOrder) {
        goodsOrder.setUserId(user.getId());
        return ResultUtils.success(goodsOrderService.createOrders(goodsOrder));
    }

    @Operation(summary = "更新订单状态：待取消")
    @PostMapping("/cancelOrdersApply")
    public BaseResponse<Boolean> cancelOrdersApply(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsOrderService.cancelOrdersApply(id));
    }

    @Operation(summary = "更新订单状态：已取消")
    @PostMapping("/cancelOrders")
    public BaseResponse<Boolean> cancelOrders(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsOrderService.cancelOrders(id));
    }

    @Operation(summary = "完成订单 / 更新订单状态：已交付")
    @PostMapping("/completeOrders")
    public BaseResponse<Boolean> completeOrders(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsOrderService.completeOrders(id));
    }

    @Operation(summary = "买家对卖家评价")
    @PostMapping("/commentGoodsUser")
    public BaseResponse<Boolean> commentGoodsUser(@RequestParam("id") Integer id,
                                                  @RequestParam("goods_user_score") String goodsUserScore,
                                                  @RequestParam("goods_user_evaluation") String goodsUserEvaluation) {
        return ResultUtils.success(goodsOrderService.commentGoodsUser(id, goodsUserScore, goodsUserEvaluation));
    }

    @Operation(summary = "卖家对买家评价")
    @PostMapping("/commentUser")
    public BaseResponse<Boolean> commentUser(@RequestParam("id") Integer id,
                                             @RequestParam("user_score") String userScore,
                                             @RequestParam("user_evaluation") String userEvaluation) {
        return ResultUtils.success(goodsOrderService.commentUser(id, userScore, userEvaluation));
    }

    @Operation(summary = "显示卖家所有订单")
    @GetMapping("/getGoodsUsersGoodsOrder")
    public BaseResponse<List<GoodsOrderInfo>> getGoodsUsersGoodsOrder(@RequestParam("goodsUserId") Integer goodsUserId) {
        return ResultUtils.success(goodsOrderService.getGoodsUsersGoodsOrder(goodsUserId));
    }

    @Operation(summary = "显示买家所有订单")
    @GetMapping("/getUsersGoodsOrder")
    public BaseResponse<List<GoodsOrderInfo>> getUsersGoodsOrder(@RequestParam("userId") Integer userId) {
        return ResultUtils.success(goodsOrderService.getUsersGoodsOrder(userId));
    }

    @Operation(summary = "显示所有订单")
    @GetMapping("/getAllGoodsOrder")
    public BaseResponse<List<GoodsOrderInfo>> getAllGoodsOrder() {
        return ResultUtils.success(goodsOrderService.getAllGoodsOrder());
    }

    @Operation(summary = "删除指定评价")
    @PostMapping("/deleteComments")
    public BaseResponse<Boolean> deleteComments(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsOrderService.deleteComments(id));
    }
}
