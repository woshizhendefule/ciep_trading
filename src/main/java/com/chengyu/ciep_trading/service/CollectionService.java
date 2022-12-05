package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.Collection;
import com.chengyu.ciep_trading.domain.vo.CollectionInfo;

import java.util.List;

/**
 * @author CL
 * @description 针对表【collection(用户商品收藏表)】的数据库操作Service
 * @createDate 2022-11-17 23:20:33
 */
public interface CollectionService extends IService<Collection> {

    /**
     * 商品收藏
     *
     * @param goodsId 商品id
     * @param userId  用户id
     * @return boolean
     */
    boolean goodsCollection(Integer userId, Integer goodsId);

    /**
     * 删除收藏
     *
     * @param id 收藏id
     * @return boolean
     */
    boolean deleteCollection(Integer id);

    /**
     * 显示用户所有收藏
     *
     * @param userId 用户id
     * @return 收藏列表
     */
    List<CollectionInfo> getUsersCollection(Integer userId);

    /**
     * 显示用户所有收藏：收藏商品用户列表查询
     *
     * @param userId 用户id
     * @return 收藏商品用户列表
     */
    List<CollectionInfo> getUsersCollectionJoinGoodsUser(Integer userId);
}
