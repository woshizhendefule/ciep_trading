package com.chengyu.ciep_trading.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.domain.Collection;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.CollectionInfo;
import com.chengyu.ciep_trading.exception.BusinessException;
import com.chengyu.ciep_trading.mapper.CollectionMapper;
import com.chengyu.ciep_trading.service.CollectionService;
import com.chengyu.ciep_trading.service.GoodsService;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 * @description 针对表【collection(用户商品收藏表)】的数据库操作Service实现
 * @createDate 2022-11-17 23:20:33
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
        implements CollectionService {

    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private CollectionMapper collectionMapper;

    @Override
    public boolean goodsCollection(Integer userId, Integer goodsId) {
        // 校验
        User user = userService.getById(userId);
        Goods goods = goodsService.getById(goodsId);
        if (user == null || goods == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断是否已经收藏
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("goods_id", goodsId);
        long count = this.count(wrapper);
        if (count >= 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户已收藏该商品");
        }

        // 插入收藏表
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setGoodsId(goodsId);
        return this.save(collection);
    }

    @Override
    public boolean deleteCollection(Integer id) {
        // 校验
        Collection collection = this.getById(id);
        if (collection == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断收藏是否存在
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", collection.getUserId());
        wrapper.eq("goods_id", collection.getGoodsId());
        long count = this.count(wrapper);
        if (count != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户未收藏该商品");
        }

        // 删除收藏
        return this.removeById(id);
    }

    @Override
    public List<CollectionInfo> getUsersCollection(Integer userId) {
        // 查询到用户所有收藏
        return this.getUsersCollectionJoinGoodsUser(userId);
    }

    @Override
    public List<CollectionInfo> getUsersCollectionJoinGoodsUser(Integer userId) {
        return collectionMapper.getUsersCollectionJoinGoodsUser(userId);
    }
}




