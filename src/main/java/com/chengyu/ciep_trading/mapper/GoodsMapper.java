package com.chengyu.ciep_trading.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.vo.GoodsInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CL
 * @description 针对表【goods(商品表)】的数据库操作Mapper
 * @createDate 2022-11-17 23:20:33
 * @Entity com.chengyu.ciep_trading.domain.Goods
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 商品用户信息浏览列表
     *
     * @return 商品用户信息列表
     */
    @Select("select g.*, u.name user_name\n" +
            "from goods g\n" +
            "         join user u on u.id = g.user_id\n" +
            "where g.is_release = 0\n" +
            "order by release_time DESC\n")
    List<GoodsInfo> getAllGoodsOrderByDescJoinUser();

    /**
     * 商品用户信息查看
     *
     * @param id 商品id
     * @return 商品用户信息
     */
    @Select("select g.*, u.name user_name\n" +
            "from goods g\n" +
            "         join user u on u.id = g.user_id\n" +
            "where g.is_release = 0\n" +
            "  and g.id = #{id}")
    GoodsInfo toViewGoodsJoinUser(@Param("id") Integer id);

    /**
     * 商品用户信息显示列表
     *
     * @return 商品用户信息列表
     */
    @Select("select g.*, u.name user_name\n" +
            "from goods g\n" +
            "         join user u on u.id = g.user_id")
    List<GoodsInfo> getAllGoodsOrderByAscJoinUser();
}