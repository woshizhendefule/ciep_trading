package com.chengyu.ciep_trading.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengyu.ciep_trading.domain.GoodsOrder;
import com.chengyu.ciep_trading.domain.vo.GoodsOrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CL
 * @description 针对表【goods_order(订单表)】的数据库操作Mapper
 * @createDate 2022-11-17 23:20:33
 * @Entity com.chengyu.ciep_trading.domain.GoodsOrder
 */
public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {
    /**
     * 订单用户商品信息列表
     *
     * @return 订单用户商品信息列表
     */
    @Select("select go.id,\n" +
            "       u.name  goods_user_name,\n" +
            "       g.name  goods_name,\n" +
            "       u2.name user_name,\n" +
            "       go.goods_user_score,\n" +
            "       go.goods_user_evaluation,\n" +
            "       go.user_score,\n" +
            "       go.user_evaluation,\n" +
            "       go.create_time,\n" +
            "       go.complete_time,\n" +
            "       go.status\n" +
            "from goods_order go\n" +
            "         join goods g on g.id = go.goods_id\n" +
            "         join user u on u.id = g.user_id\n" +
            "         join user u2 on u2.id = go.user_id;\n")
    List<GoodsOrderInfo> getAllGoodsOrderJoinUserGoods();

    /**
     * 订单用户商品信息列表(卖家所有)
     *
     * @param goodsUserId 卖家id
     * @return 订单用户商品信息列表
     */
    @Select("select go.id,\n" +
            "       u.name  goods_user_name,\n" +
            "       u.phone goods_user_phone,\n" +
            "       u2.name user_name,\n" +
            "       u2.phone user_phone,\n" +
            "       g.name  goods_name,\n" +
            "       go.goods_user_score,\n" +
            "       go.goods_user_evaluation,\n" +
            "       go.user_score,\n" +
            "       go.user_evaluation,\n" +
            "       go.create_time,\n" +
            "       go.complete_time,\n" +
            "       go.status,\n" +
            "       go.goods_user_score,\n" +
            "       go.goods_user_evaluation,\n" +
            "       go.user_score,\n" +
            "       go.user_evaluation\n" +
            "from goods_order go\n" +
            "         join goods g on g.id = go.goods_id\n" +
            "         join user u on u.id = g.user_id\n" +
            "         join user u2 on u2.id = go.user_id\n" +
            "where u.id = #{goods_user_id}")
    List<GoodsOrderInfo> getGoodsUsersGoodsOrderJoinUserGoods(@Param("goods_user_id") Integer goodsUserId);

    /**
     * 订单用户商品信息列表(买家所有)
     *
     * @param userId 买家id
     * @return 订单用户商品信息列表
     */
    @Select("select go.id,\n" +
            "       u.name  goods_user_name,\n" +
            "       u.phone goods_user_phone,\n" +
            "       u2.name user_name,\n" +
            "       u2.phone user_phone,\n" +
            "       g.name  goods_name,\n" +
            "       go.goods_user_score,\n" +
            "       go.goods_user_evaluation,\n" +
            "       go.user_score,\n" +
            "       go.user_evaluation,\n" +
            "       go.create_time,\n" +
            "       go.complete_time,\n" +
            "       go.status,\n" +
            "       go.goods_user_score,\n" +
            "       go.goods_user_evaluation,\n" +
            "       go.user_score,\n" +
            "       go.user_evaluation\n" +
            "from goods_order go\n" +
            "         join goods g on g.id = go.goods_id\n" +
            "         join user u on u.id = g.user_id\n" +
            "         join user u2 on u2.id = go.user_id\n" +
            "where u2.id = #{user_id}")
    List<GoodsOrderInfo> getUsersGoodsOrderJoinUserGoods(@Param("user_id") Integer userId);


}




