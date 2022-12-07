package com.chengyu.ciep_trading.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengyu.ciep_trading.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author CL
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2022-11-18 20:15:41
 * @Entity com.chengyu.ciep_trading.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 卖家用户评分信息
     *
     * @param id 用户id
     * @return 用户脱敏评分信息
     */
    @Select("select avg(a.goods_user_score) avg_goods_user_score\n" +
            "from (select go.goods_user_score\n" +
            "      from user u\n" +
            "               join goods g on u.id = g.user_id\n" +
            "               join goods_order go on g.id = go.goods_id\n" +
            "      where u.id = #{id}) as a;")
    Double getAvgGoodsUserScoreJoinGoodsGoodsOrder(@Param("id") Integer id);

    /**
     * 买家用户评分信息
     *
     * @param id 用户id
     * @return 用户脱敏评分信息
     */
    @Select("select avg(a.user_score) avg_user_score\n" +
            "from (select go.user_score\n" +
            "      from user u\n" +
            "               join goods g on u.id = g.user_id\n" +
            "               join goods_order go on g.id = go.goods_id\n" +
            "      where u.id = #{id}) as a;")
    Double getAvgUserScoreJoinGoodsGoodsOrder(@Param("id") Integer id);
}




