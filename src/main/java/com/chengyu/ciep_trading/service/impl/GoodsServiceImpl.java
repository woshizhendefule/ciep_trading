package com.chengyu.ciep_trading.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.mapper.GoodsMapper;
import com.chengyu.ciep_trading.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CL
 * @description 针对表【goods(商品表)】的数据库操作Service实现
 * @createDate 2022-11-17 23:20:33
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    @Override
    public List<Goods> getAllGoodsOrderBy() {
        // 判断有无商品,查询到所有商品
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("release_time");
        return this.list(wrapper);
    }


}




