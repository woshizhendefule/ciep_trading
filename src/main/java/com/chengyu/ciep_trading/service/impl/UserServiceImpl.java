package com.chengyu.ciep_trading.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.domain.vo.UserInfo;
import com.chengyu.ciep_trading.exception.BusinessException;
import com.chengyu.ciep_trading.mapper.UserMapper;
import com.chengyu.ciep_trading.service.UserService;
import com.chengyu.ciep_trading.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CL
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-11-18 20:15:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String userLogin(String name, String password) {
        // 校验
        if (StrUtil.hasBlank(name, password)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (this.getOne(wrapper) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该用户");
        }

        // 密码是否正确
        wrapper.eq("password", password);
        User user = this.getOne(wrapper);
        if (user == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "密码错误");
        }

        // 判断是否为用户
        if (user.getIsAdmin() != 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该账户不为用户");
        }
        return JwtUtils.generateToken(user.getId());
    }

    @Override
    public String adminLogin(String name, String password) {
        // 校验
        if (StrUtil.hasBlank(name, password)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (this.getOne(wrapper) == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "没有查找到该用户");
        }

        // 密码是否正确
        wrapper.eq("password", password);
        User user = this.getOne(wrapper);
        if (user == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "密码错误");
        }

        // 判断是否为管理员
        if (user.getIsAdmin() != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "该账户不为管理员");
        }
        return JwtUtils.generateToken(user.getId());
    }

    @Override
    public boolean registered(String name, String password, String studentId, String phone) {
        // 校验
        if (StrUtil.hasBlank(name, password, studentId, phone)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name)
                .or().eq("student_id", studentId);
        long count = this.count(wrapper);
        if (count >= 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "用户名已存在");
        }

        // 注册用户表
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setStudentId(studentId);
        user.setPhone(phone);
        return this.save(user);
    }

    @Override
    public boolean retrievePassword(String name, String studentId, String phone, String newPassword) {
        // 校验
        if (StrUtil.hasBlank(name, studentId, phone, newPassword)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断用户名 / 学号 / 手机是否正确
        QueryWrapper<User> wrapperTh = new QueryWrapper<>();
        wrapperTh.eq("name", name)
                .eq("student_id", studentId)
                .eq("phone", phone);
        long count = this.count(wrapperTh);
        if (count != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "用户名、学号、手机不正确");
        }

        // 更新（新）密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", name);
        wrapper.set("password", newPassword);
        return this.update(wrapper);
    }

    @Override
    public boolean changePassword(Integer id, String newPassword) {
        // 校验
        if (StrUtil.hasBlank(newPassword)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 更新（新）密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("password", newPassword);
        return this.update(wrapper);
    }

    @Override
    public UserInfo toViewUserInfo(Integer id) {
        // 信息查看
        return new UserInfo(this.getById(id));
    }

    @Override
    public boolean modifyUserName(Integer id, String newName) {
        // 校验
        if (StrUtil.hasBlank(newName)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 判断（name）唯一
        UpdateWrapper<User> wrapperNa = new UpdateWrapper<>();
        wrapperNa.eq("name", newName);
        User userNa = this.getOne(wrapperNa);
        System.out.println(userNa);

        if (userNa != null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "用户名已存在");
        }

        // 修改（name）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("name", newName);
        return this.update(wrapper);
    }

    @Override
    public boolean modifyUserPhone(Integer id, String newPhone) {
        // 校验
        if (StrUtil.hasBlank(newPhone)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        // 修改（phone）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("phone", newPhone);
        return this.update(wrapper);
    }

    @Override
    public boolean sellerQualificationApply(Integer id) {
        // 更新用户类型（ 0 → 2 ）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("is_seller", 2);
        return this.update(wrapper);
    }

    @Override
    public List<UserInfo> getAllUser() {
        // 查询到所有用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("is_admin", 0);
        List<User> list = this.list(wrapper);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (User user : list) {
            UserInfo userInfo = new UserInfo(user);
            Integer id = user.getId();
            userInfo.setGoodsUserScore(this.getAvgGoodsUserScoreJoinGoodsGoodsOrder(id));
            userInfo.setUserScore(this.getAvgUserScoreJoinGoodsGoodsOrder(id));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public Double getAvgGoodsUserScoreJoinGoodsGoodsOrder(Integer id) {
        return userMapper.getAvgGoodsUserScoreJoinGoodsGoodsOrder(id);
    }

    @Override
    public Double getAvgUserScoreJoinGoodsGoodsOrder(Integer id) {
        return userMapper.getAvgUserScoreJoinGoodsGoodsOrder(id);
    }

    @Override
    public boolean deleteUser(Integer id) {
        // 删除用户
        return this.removeById(id);
    }

    @Override
    public boolean sellerQualificationCheckPass(Integer id) {
        // 更新用户类型（ 2 → 1 ）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("is_seller", 1);
        return this.update(wrapper);
    }

    @Override
    public boolean sellerQualificationCheckNotPass(Integer id) {
        // 更新用户类型（ 2 → 0 ）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("is_seller", 0);
        return this.update(wrapper);
    }
}