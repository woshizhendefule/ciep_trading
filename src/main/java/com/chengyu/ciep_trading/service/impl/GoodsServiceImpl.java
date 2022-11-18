package com.chengyu.ciep_trading.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.service.GoodsService;
import com.chengyu.ciep_trading.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author CL
* @description 针对表【goods(商品表)】的数据库操作Service实现
* @createDate 2022-11-17 23:20:33
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




