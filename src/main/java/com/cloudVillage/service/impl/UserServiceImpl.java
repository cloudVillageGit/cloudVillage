package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.cloudVillage.mapper.UserMapper;
import com.cloudVillage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 处理登录逻辑
     * @param openId 每个用户只有一个，判断是否为第一次注册
     * @param userToken 每个用户每一时间只有一个，判断登陆状态是否过期
     * @return 数据结果
     */
    @Override
    public ResponseResult login(String openId, String userToken) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        // 将openId和token插入数据库表，先查询数据库表中是否已存在此openId

        System.out.println(openId);
        userQueryWrapper.eq("openId",openId);
        // openId 对应每个公众号（小程序？）唯一
        List<User> users = userMapper.selectList(userQueryWrapper);

        User user = new User();
        if (users.size()!=0) {
            user = users.get(0);
        }

        // 数据库中已存在此openId，这时只需要更新token
        if (users.size()!=0) {
            Integer id = user.getId();
            userUpdateWrapper.eq("id", id);
            userUpdateWrapper.set("userToken", userToken);// 更新token
            System.out.println("新token:"+userToken);
            if (userMapper.update(null, userUpdateWrapper) == 1) {
                // 更新成功，把新token存入前端缓存
                // 刷新user
                List<User> _users = userMapper.selectList(userQueryWrapper);
                user = _users.get(0);
                return new ResponseResult(200, "更新token成功",user);
            } else {
                return new ResponseResult(500, "登录失败，更新Token错误");
            }
        } else {
            //不存在此openId，则先添加一个新用户，然后将新用户的id与用户登录表关联
            // 添加基本属性
            user.setUserToken(userToken);
            user.setOpenid(openId);
            user.setDelLogic(0);
            // 添加新用户
            int insertStatus = userMapper.insert(user);
            if (insertStatus == 1) { // 新用户添加成功后，插入一条登录信息，关联openId、token及userId
                return new ResponseResult(200, "登录成功",user);
            } else {
                return new ResponseResult(500, "登录失败，添加openId及token时错误");
            }
        }
    }

    @Override
    public int checkToken(String userToken) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userQueryWrapper.eq("userToken", userToken);
        List<User> users = userMapper.selectList(userQueryWrapper);
        User user = new User();
        if(users.size()!=0){
            user = users.get(0);
        }
        if(users.size()!=0){
            return user.getId();
        }else{
            return -1;
        }
    }
}
