package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.service.GoodsOrderService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CL
 */
@RestController
@Api(tags = "goods_order(订单表) Controller")
@RequestMapping("/goods_order")
public class GoodsOrderController {

    @Resource
    private GoodsOrderService goodsOrderService;
}
