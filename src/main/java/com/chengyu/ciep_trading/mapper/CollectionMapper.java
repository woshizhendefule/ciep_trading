package com.chengyu.ciep_trading.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengyu.ciep_trading.domain.Collection;
import com.chengyu.ciep_trading.domain.vo.CollectionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CL
 * @description 针对表【collection(用户商品收藏表)】的数据库操作Mapper
 * @createDate 2022-11-17 23:20:33
 * @Entity com.chengyu.ciep_trading.domain.Collection
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    /**
     * 收藏商品用户列表
     *
     * @param userId 用户id
     * @return 收藏商品用户列表
     */
    @Select("select g.name      goods_name,\n" +
            "       g.introduce goods_introduce,\n" +
            "       g.picture   goods_picture,\n" +
            "       g.price     goods_price,\n" +
            "       u.name      user_name,\n" +
            "       u.phone     user_phone\n" +
            "from user u\n" +
            "         join collection c on u.id = c.user_id\n" +
            "         join goods g on c.goods_id = g.id\n" +
            "where u.id = #{user_id};")
    List<CollectionInfo> getUsersCollectionJoinGoodsUser(@Param("user_id") Integer userId);

}




