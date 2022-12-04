package com.chengyu.ciep_trading.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.domain.Goods;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.exception.BusinessException;
import com.chengyu.ciep_trading.mapper.GoodsMapper;
import com.chengyu.ciep_trading.service.GoodsService;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CL
 * @description 针对表【goods(商品表)】的数据库操作Service实现
 * @createDate 2022-11-17 23:20:33
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    @Resource
    private UserService userService;

    public static final int CHECK_LINE = 800;

    @Override
    public List<Goods> getAllGoodsOrderByDesc() {
        // 查询到所有商品
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("release_time");
        return this.list(wrapper);
    }

    @Override
    public Goods toViewGoods(Integer id) {
        // 判断商品有无存在
        Goods byId = this.getById(id);
        if (byId == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该商品");
        }

        // 信息查看
        return byId;
    }

    @Override
    public List<Goods> searchGoods(String name) {
        // 校验
        if (StrUtil.hasBlank(name)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断商品有无存在，查询到所有商品
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return this.list(wrapper);
    }

    @Override
    public boolean releaseGoods(Integer userId, Goods goods) {
        // 校验
        if (StrUtil.hasBlank(goods.getName())) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (goods.getPrice() == null || goods.getPrice() == 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (goods.getPrice() < 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数输入错误");
        }

        // 判断用户是否为卖家
        User user = userService.getById(userId);
        if (user.getIsSeller() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户不为卖家");
        }

        // 插入商品表
        goods.setUserId(userId);
        // TODO 商品图片上传
        // TODO 商品凭证上传

        // 以800元为界限，设置商品审核状态（≥ 800 - 2 / < 800 - 0）
        if (goods.getPrice() >= CHECK_LINE) {
            goods.setIsRelease(2);
        }
        return this.save(goods);
    }

    @Override
    public List<Goods> getUsersGoods(Integer userId) {
        // 判断用户是否为卖家
        User user = userService.getById(userId);
        if (user.getIsSeller() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户不为卖家");
        }

        // 查询到所有用户商品
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.list(wrapper);
    }

    @Override
    public boolean modifyGoods(Integer userId, Goods goods) {
        // 校验
        if (goods.getId() == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (StrUtil.hasBlank(goods.getName())) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (goods.getPrice() == null || goods.getPrice() == 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }
        if (goods.getPrice() < 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数输入错误");
        }

        // 判断用户是否为卖家
        User user = userService.getById(userId);
        if (user.getIsSeller() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户不为卖家");
        }

        // 修改商品信息
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", goods.getId());
        wrapper.set("name", goods.getName());
        wrapper.set("introduce", goods.getIntroduce());
        // TODO 商品图片修改
        // TODO 商品凭证修改
        wrapper.set("price", goods.getPrice());

        // 更新：以800元为界限，设置商品审核状态（≥ 800 - 2 / < 800 - 0）
        if (goods.getPrice() >= CHECK_LINE) {
            goods.setIsRelease(2);
        } else {
            goods.setIsRelease(0);
        }
        wrapper.set("is_release", goods.getIsRelease());

        return this.update(wrapper);
    }

    @Override
    public boolean deleteGoods(Integer id) {
        // 判断用户是否为卖家
        Goods goods = this.getById(id);
        Integer userId = goods.getUserId();
        User user = userService.getById(userId);
        if (user.getIsSeller() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该用户不为卖家");
        }

        // 删除商品
        return this.removeById(id);
    }

    @Override
    public List<Goods> getAllGoodsOrderByAsc() {
        // 查询到所有商品
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("release_time");
        return this.list(wrapper);
    }

    @Override
    public boolean releaseQualificationCheckPass(Integer id) {
        // 更新商品状态（ 2 → 0 ）
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("is_release", 0);
        return this.update(wrapper);
    }

    @Override
    public boolean releaseQualificationCheckNotPass(Integer id) {
        // 更新商品状态（ 2 → 1 ）
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("is_release", 1);
        return this.update(wrapper);
    }
}