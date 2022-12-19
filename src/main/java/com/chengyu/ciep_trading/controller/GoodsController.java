package com.chengyu.ciep_trading.controller;

import cn.hutool.core.util.StrUtil;
import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultUtils;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.GoodsInfo;
import com.chengyu.ciep_trading.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 */
@RestController
@Api(tags = "goods(商品表) Controller")
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Operation(summary = "浏览商品（发布时间降序）")
    @GetMapping("/getAllGoodsOrderByDesc")
    public BaseResponse<List<GoodsInfo>> getAllGoodsOrderByDesc() {
        return ResultUtils.success(goodsService.getAllGoodsOrderByDesc());
    }

    @Operation(summary = "查看商品信息")
    @GetMapping("/toViewGoods")
    public BaseResponse<GoodsInfo> toViewGoods(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsService.toViewGoods(id));
    }

    @Operation(summary = "搜索商品")
    @GetMapping("/searchGoods")
    public BaseResponse<List<Goods>> searchGoods(@RequestParam("name") String name) {
        return ResultUtils.success(goodsService.searchGoods(name));
    }

    @Operation(summary = "发布商品")
    @PostMapping("/releaseGoods")
    public BaseResponse<Boolean> releaseGoods(User user,
                                              @RequestParam("name") String name,
                                              @RequestParam("introduce") String introduce,
                                              @RequestParam("price") Double price,
                                              @RequestParam("picture") MultipartFile picture,
                                              @RequestParam("credential") MultipartFile credential) {
        Goods goods = new Goods();
        goods.setName(StrUtil.sub(name, 0, -1));
        goods.setIntroduce(StrUtil.sub(introduce, 0, -1));
        goods.setPrice(price);
        return ResultUtils.success(goodsService.releaseGoods(user.getId(), goods, picture, credential));
    }

    @Operation(summary = "显示用户所有商品")
    @GetMapping("/getUsersGoods")
    public BaseResponse<List<Goods>> getUsersGoods(User user) {
        return ResultUtils.success(goodsService.getUsersGoods(user.getId()));
    }

    @Operation(summary = "修改商品信息")
    @PostMapping("/modifyGoods")
    public BaseResponse<Boolean> modifyGoods(User user,
                                             @RequestParam("id") Integer id,
                                             @RequestParam("name") String name,
                                             @RequestParam("introduce") String introduce,
                                             @RequestParam("price") Double price,
                                             @RequestParam("picture") MultipartFile picture,
                                             @RequestParam("credential") MultipartFile credential) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(StrUtil.sub(name, 0, -1));
        goods.setIntroduce(StrUtil.sub(introduce, 0, -1));
        goods.setPrice(price);
        return ResultUtils.success(goodsService.modifyGoods(user.getId(), goods, picture, credential));
    }

    @Operation(summary = "删除商品")
    @PostMapping("/deleteGoods")
    public BaseResponse<Boolean> deleteGoods(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsService.deleteGoods(id));
    }

    @Operation(summary = "显示所有商品")
    @GetMapping("/getAllGoodsOrderByAsc")
    public BaseResponse<List<GoodsInfo>> getAllGoodsOrderByAsc() {
        return ResultUtils.success(goodsService.getAllGoodsOrderByAsc());
    }

    @Operation(summary = "发布资质审核通过")
    @PostMapping("/releaseQualificationCheckPass")
    public BaseResponse<Boolean> releaseQualificationCheckPass(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsService.releaseQualificationCheckPass(id));
    }

    @Operation(summary = "发布资质审核未通过")
    @PostMapping("/releaseQualificationCheckNotPass")
    public BaseResponse<Boolean> releaseQualificationCheckNotPass(@RequestParam("id") Integer id) {
        return ResultUtils.success(goodsService.releaseQualificationCheckNotPass(id));
    }
}
