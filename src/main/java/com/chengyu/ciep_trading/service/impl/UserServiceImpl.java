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

    @Override
    public String login(String name, String password) {
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
    public boolean changePassword(String name, String oldPassword, String newPassword) {
        // 校验
        if (StrUtil.hasBlank(name, oldPassword, newPassword)) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "参数为空");
        }

        //判断用户名 / （旧）密码是否存在
        QueryWrapper<User> wrapperTh = new QueryWrapper<>();
        wrapperTh.eq("name", name)
                .eq("password", oldPassword);
        long count = this.count(wrapperTh);
        if (count != 1) {
            throw new BusinessException(ResultCode.PARAMS_ERROR, "操作用户不存在");
        }

        // 更新（新）密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", name);
        wrapper.set("password", newPassword);
        return this.update(wrapper);
    }

    @Override
    public UserInfo toViewUserInfo() {
        // 判断用户是否存在
        User byId = this.getById(5);
        if (byId == null) {
            return null;
        }

        // 信息查看
        return new UserInfo(byId);
    }

    @Override
    public boolean modifyUser(User user) {
        // 校验
        if (user.getId() == null && user.getId() < 0) {
            return false;
        }

        // 验证用户是否存在
        User byId = this.getById(user.getId());
        if (byId == null) {
            return false;
        }

        // 判断（name）唯一
        UpdateWrapper<User> wrapperNa = new UpdateWrapper<>();
        wrapperNa.eq("name", user.getName());
        long count = this.count(wrapperNa);
        if (count >= 1) {
            return false;
        }

        // 修改（name / phone）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", user.getId());
        wrapper.set("name", user.getName());
        wrapper.set("phone", user.getPhone());
        return this.update(wrapper);
    }

    @Override
    public boolean sellerQualificationApply() {
        // 更新用户类型（ 0 → 2 ）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 5);
        wrapper.set("is_seller", 2);
        return this.update(wrapper);
    }

    @Override
    public List<UserInfo> getAllUser() {
        // 判断有无用户，查询到所有待审核用户
        List<User> list = this.list();
        List<UserInfo> userInfoList = new ArrayList<>();
        for (User user : list) {
            userInfoList.add(new UserInfo(user));
        }
        return userInfoList;
    }

    @Override
    public boolean deleteUser() {
        // 判断用户是否存在
        User user = this.getById(10);
        if (user == null) {
            return false;
        }
        // 删除用户
        return this.removeById(10);
    }

    @Override
    public boolean sellerQualificationCheck() {
        // 更新用户类型（ 2 → 1 ）
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 5);
        wrapper.set("is_seller", 1);
        return this.update(wrapper);
    }


}