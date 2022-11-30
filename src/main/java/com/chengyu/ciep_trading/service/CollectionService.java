package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.Collection;

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
    boolean goodsCollection(Integer goodsId, Integer userId);


}
