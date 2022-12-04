package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.Goods;

import java.util.List;

/**
 * @author CL
 * @description 针对表【goods(商品表)】的数据库操作Service
 * @createDate 2022-11-17 23:20:33
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 浏览商品（发布时间降序）
     *
     * @return 商品列表
     */
    List<Goods> getAllGoodsOrderByDesc();

    /**
     * 查看商品信息
     *
     * @param id 商品id
     * @return Goods
     */
    Goods toViewGoods(Integer id);

    /**
     * 搜索商品
     *
     * @param name 商品名称
     * @return 商品列表
     */
    List<Goods> searchGoods(String name);

    /**
     * 发布商品
     *
     * @param userId 用户id
     * @param goods  商品信息
     * @return boolean
     */
    boolean releaseGoods(Integer userId, Goods goods);

    /**
     * 显示用户所有商品
     *
     * @param userId 用户id
     * @return 商品列表
     */
    List<Goods> getUsersGoods(Integer userId);

    /**
     * 修改商品信息
     *
     * @param userId 用户id
     * @param goods  商品信息
     * @return boolean
     */
    boolean modifyGoods(Integer userId, Goods goods);

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return boolean
     */
    boolean deleteGoods(Integer id);

    /**
     * 显示所有商品
     *
     * @return 商品列表
     */
    List<Goods> getAllGoodsOrderByAsc();

    /**
     * 发布资质审核通过
     *
     * @param id 商品id
     * @return boolean
     */
    boolean releaseQualificationCheckPass(Integer id);

    /**
     * 发布资质审核未通过
     *
     * @param id 商品id
     * @return boolean
     */
    boolean releaseQualificationCheckNotPass(Integer id);
}
