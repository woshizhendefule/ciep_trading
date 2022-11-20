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
     * @param
     * @return
     */
    List<Goods> getAllGoodsOrderBy();

}
