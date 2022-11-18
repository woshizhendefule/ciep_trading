package com.chengyu.ciep_trading.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.domain.Collection;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.service.CollectionService;
import com.chengyu.ciep_trading.mapper.CollectionMapper;
import com.chengyu.ciep_trading.service.GoodsService;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author CL
* @description 针对表【collection(用户商品收藏表)】的数据库操作Service实现
* @createDate 2022-11-17 23:20:33
*/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;

    @Override
    public boolean goodsCollection(Integer goodsId, Integer userId) {
        // 校验
        User user = userService.getById(userId);
        Goods goods = goodsService.getById(goodsId);
        if (user == null || goods == null) {
            return false;
        }

        // 判断是否已经收藏
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("goods_id", goodsId);
        long count = this.count(wrapper);
        if (count >= 1) {
            return false;
        }

        // 插入收藏表
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setGoodsId(goodsId);
        return this.save(collection);
    }


}




