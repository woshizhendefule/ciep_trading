package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("/getAllGoodsOrderBy")
    public BaseResponse<List<Goods>> getAllGoodsOrderBy() {
        return ResultUtils.success(goodsService.getAllGoodsOrderBy());
    }

    @GetMapping("/toViewGoods")
    public BaseResponse<Goods> toViewGoods(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsService.toViewGoods(id));
    }


}
