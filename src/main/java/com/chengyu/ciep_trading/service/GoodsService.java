package com.chengyu.ciep_trading.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.vo.GoodsInfo;
import org.springframework.web.multipart.MultipartFile;

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
     * @return 商品用户信息列表
     */
    List<GoodsInfo> getAllGoodsOrderByDesc();

    /**
     * 浏览商品（发布时间降序）: 商品用户信息列表
     *
     * @return 商品用户信息列表
     */
    List<GoodsInfo> getAllGoodsOrderByDescJoinUser();

    /**
     * 查看商品信息
     *
     * @param id 商品id
     * @return GoodsInfo
     */
    GoodsInfo toViewGoods(Integer id);

    /**
     * 查看商品信息: 商品用户信息查看
     *
     * @param id 商品id
     * @return GoodsInfo
     */
    GoodsInfo toViewGoodsJoinUser(Integer id);

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
     * @param userId     用户id
     * @param goods      商品信息
     * @param picture    商品图片
     * @param credential 商品凭证
     * @return boolean
     */
    boolean releaseGoods(Integer userId, Goods goods, MultipartFile picture, MultipartFile credential);

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
     * @param userId     用户id
     * @param goods      商品信息
     * @param picture    商品图片
     * @param credential 商品凭证
     * @return boolean
     */
    boolean modifyGoods(Integer userId, Goods goods, MultipartFile picture, MultipartFile credential);

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
     * @return 商品用户信息列表
     */
    List<GoodsInfo> getAllGoodsOrderByAsc();

    /**
     * 显示所有商品: 商品用户信息显示列表
     *
     * @return 商品用户信息列表
     */
    List<GoodsInfo> getAllGoodsOrderByAscJoinUser();

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
