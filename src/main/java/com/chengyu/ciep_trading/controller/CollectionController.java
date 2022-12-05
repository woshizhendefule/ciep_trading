package com.chengyu.ciep_trading.controller;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.CollectionInfo;
import com.chengyu.ciep_trading.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@Api(tags = "collection(用户商品收藏表) Controller")
@RequestMapping("/collection")
public class CollectionController {

    @Resource
    private CollectionService goodsCollection;

    @Operation(summary = "商品收藏")
    @PostMapping("/goodsCollection")
    public BaseResponse<Boolean> goodsCollection(User user, @RequestParam("goodsId") Integer goodsId) {
        return ResultUtils.success(goodsCollection.goodsCollection(user.getId(), goodsId));
    }

    @Operation(summary = "删除收藏")
    @PostMapping("/deleteCollection")
    public BaseResponse<Boolean> deleteCollection(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsCollection.deleteCollection(id));
    }

    @Operation(summary = "显示用户所有收藏")
    @GetMapping("/getUsersGoods")
    public BaseResponse<List<CollectionInfo>> getUsersCollection(User user) {
        return ResultUtils.success(goodsCollection.getUsersCollection(user.getId()));
    }
}