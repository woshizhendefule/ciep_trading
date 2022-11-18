package com.chengyu.ciep_trading.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengyu.ciep_trading.domain.User;
import com.chengyu.ciep_trading.mapper.UserMapper;
import com.chengyu.ciep_trading.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author CL
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-11-18 20:15:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public boolean login(String name, String password) {
        // 校验
        if (StrUtil.hasBlank(name, password)) {
            return false;
        }

        // 用户名密码是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name)
                .eq("password", password);
        long count = this.count(wrapper);
        return count == 1;
    }

    @Override
    public boolean registered(String name, String password, String studentId, String phone) {
        // 校验
        if (StrUtil.hasBlank(name, password, studentId, phone)) {
            return false;
        }

        // 判断用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name)
                .or().eq("student_id", studentId);
        long count = this.count(wrapper);
        if (count >= 1) {
            return false;
        }

        // 插入用户表
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setStudentId(studentId);
        user.setPhone(phone);
        boolean save = this.save(user);
        if (!save) {
            return false;
        }

        return true;
    }

    @Override
    public boolean retrievePassword(String name, String studentId, String phone, String newPassword) {
        // 校验
        if (StrUtil.hasBlank(name, studentId, phone, newPassword)) {
            return false;
        }

        // 判断用户名 / 学号 / 手机是否存在
        QueryWrapper<User> wrapperTh = new QueryWrapper<>();
        wrapperTh.eq("name", name)
                .or().eq("student_id", studentId)
                .or().eq("phone", phone);
        long count = this.count(wrapperTh);
        if (count != 1) {
            return false;
        }

        // 插入（新）密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", name);
        wrapper.set("password", newPassword);
        return this.update(wrapper);
    }

    @Override
    public boolean changePassword(String name, String oldPassword, String newPassword) {
        // 校验
        if (StrUtil.hasBlank(name, oldPassword, newPassword)) {
            return false;
        }

        //判断用户名 / （旧）密码是否存在
        QueryWrapper<User> wrapperTh = new QueryWrapper<>();
        wrapperTh.eq("name", name)
                .eq("password", oldPassword);
        long count = this.count(wrapperTh);
        if (count != 1) {
            return false;
        }

        // 更新（新）密码
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", name);
        wrapper.set("password", newPassword);
        return this.update(wrapper);
    }
}




