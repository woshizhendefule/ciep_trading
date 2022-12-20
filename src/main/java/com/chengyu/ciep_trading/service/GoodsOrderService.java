package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.GoodsOrder;
import com.chengyu.ciep_trading.domain.vo.GoodsOrderInfo;

import java.util.List;

/**
 * @author CL
 * @description 针对表【goods_order(订单表)】的数据库操作Service
 * @createDate 2022-11-17 23:20:33
 */
public interface GoodsOrderService extends IService<GoodsOrder> {

    /**
     * 创建订单
     *
     * @param goodsOrder 订单信息
     * @return boolean
     */
    boolean createOrders(GoodsOrder goodsOrder);

    /**
     * 更新订单状态：待取消
     *
     * @param id 订单id
     * @return boolean
     */
    boolean cancelOrdersApply(Integer id);

    /**
     * 更新订单状态：已取消
     *
     * @param id 订单id
     * @return boolean
     */
    boolean cancelOrders(Integer id);

    /**
     * 完成订单 / 更新订单状态：已交付
     *
     * @param id 订单id
     * @return boolean
     */
    boolean completeOrders(Integer id);

    /**
     * 买家对卖家评价
     *
     * @param id                  订单id
     * @param goodsUserScore      卖家评分（五星制）
     * @param goodsUserEvaluation 卖家评价
     * @return boolean
     */
    boolean commentGoodsUser(Integer id, String goodsUserScore, String goodsUserEvaluation);

    /**
     * 卖家对买家评价
     *
     * @param id             订单id
     * @param userScore      买家评分（五星制）
     * @param userEvaluation 买家评价
     * @return boolean
     */
    boolean commentUser(Integer id, String userScore, String userEvaluation);

    /**
     * 显示所有订单
     *
     * @return 订单用户商品信息列表
     */
    List<GoodsOrderInfo> getUsersGoodsOrder();

    /**
     * 显示所有订单：订单用户商品信息列表
     *
     * @return 订单用户商品信息列表
     */
    List<GoodsOrderInfo> getUsersGoodsOrderJoinUserGoods();

    /**
     * 删除指定评价
     *
     * @param id 订单id
     * @return boolean
     */
    boolean deleteComments(Integer id);
}
