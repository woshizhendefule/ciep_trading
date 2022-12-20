package com.chengyu.ciep_trading.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.GoodsOrder;
import com.chengyu.ciep_trading.domain.vo.GoodsOrderInfo;
import com.chengyu.ciep_trading.exception.BusinessException;
import com.chengyu.ciep_trading.mapper.GoodsOrderMapper;
import com.chengyu.ciep_trading.service.GoodsOrderService;
import com.chengyu.ciep_trading.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author CL
 * @description 针对表【goods_order(订单表)】的数据库操作Service实现
 * @createDate 2022-11-17 23:20:33
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder>
        implements GoodsOrderService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsOrderMapper goodsOrderMapper;

    @Override
    public boolean createOrders(GoodsOrder goodsOrder) {
        // 校验
        if (goodsService.getById(goodsOrder.getGoodsId()) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该商品");
        }

        // 更新商品状态（ 0 → 3 ）
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", goodsOrder.getGoodsId());
        wrapper.set("is_release", 3);
        boolean update = goodsService.update(wrapper);
        if (!update) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "更新商品状态失败");
        }

        // 插入订单表
        return this.save(goodsOrder);
    }

    @Override
    public boolean cancelOrdersApply(Integer id) {
        // 更新订单状态（ 0 → 3 ）
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("status", 3);
        return this.update(wrapper);
    }

    @Override
    public boolean cancelOrders(Integer id) {
        // 更新商品状态（ 3 → 0 ）
        GoodsOrder goodsOrder = this.getById(id);
        UpdateWrapper<Goods> wrapperIs = new UpdateWrapper<>();
        wrapperIs.eq("id", goodsOrder.getGoodsId());
        wrapperIs.set("is_release", 0);
        boolean update = goodsService.update(wrapperIs);
        if (!update) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "更新商品状态失败");
        }

        // 更新订单状态（ 3 → 2 ）
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("status", 2);
        return this.update(wrapper);
    }

    @Override
    public boolean completeOrders(Integer id) {
        // 更新订单状态（ 0 → 1 ）
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("status", 1);

        // 添加订单完成时间
        wrapper.set("complete_time", new Timestamp(System.currentTimeMillis()));
        return this.update(wrapper);
    }

    @Override
    public boolean commentGoodsUser(Integer id, String goodsUserScore, String goodsUserEvaluation) {
        // 校验
        if (this.getById(id) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该订单");
        }
        if (StrUtil.hasBlank(goodsUserScore, goodsUserEvaluation)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断订单状态为 1
        if (this.getById(id).getStatus() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该订单未交付");
        }

        // 插入订单表
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("goods_user_score", goodsUserScore);
        wrapper.set("goods_user_evaluation", goodsUserEvaluation);
        return this.update(wrapper);
    }

    @Override
    public boolean commentUser(Integer id, String userScore, String userEvaluation) {
        // 校验
        if (this.getById(id) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该订单");
        }
        if (StrUtil.hasBlank(userScore, userEvaluation)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断订单状态为 1
        if (this.getById(id).getStatus() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该订单未交付");
        }

        // 插入订单表
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("user_score", userScore);
        wrapper.set("user_evaluation", userEvaluation);
        return this.update(wrapper);
    }

    @Override
    public List<GoodsOrderInfo> getUsersGoodsOrder() {
        // 查询到所有订单
        return this.getUsersGoodsOrderJoinUserGoods();
    }

    @Override
    public List<GoodsOrderInfo> getUsersGoodsOrderJoinUserGoods() {
        return goodsOrderMapper.getUsersGoodsOrderJoinUserGoods();
    }

    @Override
    public boolean deleteComments(Integer id) {
        // 校验
        if (this.getById(id) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该订单");
        }

        // 删除评价
        UpdateWrapper<GoodsOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("goods_user_score", 0);
        wrapper.set("goods_user_evaluation", null);
        wrapper.set("user_score", 0);
        wrapper.set("user_evaluation", null);
        return this.update(wrapper);
    }
}




