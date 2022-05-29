package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.entity.crossResult.UserInfo;
import com.cloudVillage.mapper.UserMapper;
import com.cloudVillage.mapper.UserRealInfoMapper;
import com.cloudVillage.service.IUserRealInfoService;
import com.cloudVillage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserRealInfoMapper userRealInfoMapper;
    @Autowired
    private IUserRealInfoService userRealInfoService;


    /**
     * 处理登录逻辑
     * @param openId 每个用户只有一个，判断是否为第一次注册
     * @param userToken 每个用户每一时间只有一个，判断登陆状态是否过期
     * @return 数据结果
     */
    @Override
    public ResponseResult login(String openId, String userToken,String avatarUrl,String nickName) {

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

        // 数据库中已存在此openId，这时只需要更新token,头像，与此时的名称
        if (users.size()!=0) {
            Integer id = user.getId();
            userUpdateWrapper.eq("id", id);
            userUpdateWrapper.set("userToken", userToken);// 更新token
            userUpdateWrapper.set("avatarUrl",avatarUrl);// 更新头像
            userUpdateWrapper.set("nickName",nickName);// 更新昵称
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
            user.setNickname(nickName);
            user.setAvatarurl(avatarUrl);
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

    @Override
    public int updateUserInfo(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        int update = userMapper.update(user, updateWrapper);
        return update;
    }

    /**
     * 删除用户个人信息 如果关联了个人真实信息表，一并删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteUser(Integer id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",id);
        List<User> users = userMapper.selectList(userQueryWrapper);
        // 根据id查找用户信息表没有找到人时，返回错误
        if(users.size() == 0){
            return new ResponseResult(500,"查询用户个人信息表错误");
        }
        User user = users.get(0);
        Integer userinfoid = user.getUserinfoid();
        if(userinfoid == null){
            int userDelete = userMapper.deleteById(id);
            return new ResponseResult(200,"该用户未完成个人信息填写,已删除个人信息");
        }
        QueryWrapper<UserRealInfo> userRealInfoQueryWrapper = new QueryWrapper<>();
        userRealInfoQueryWrapper.eq("id",userinfoid);
        List<UserRealInfo> userRealInfos = userRealInfoMapper.selectList(userRealInfoQueryWrapper);
        if(userRealInfos.size() == 0){
            return new ResponseResult(500,"用户真实信息已逻辑删除");
        }
        int userDelete = userMapper.deleteById(id);
        int userInfoDelete = userRealInfoMapper.deleteById(userinfoid);
        return new ResponseResult(200,"删除成功");
    }

    @Override
    public ResponseResult getAllUserAllInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        List<User> users = userMapper.selectList(null);
        if(users.size()==0){
            return new ResponseResult(500,"查询失败，无数据");
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserInfo userInfo = new UserInfo();
            userInfo.setUser(user);
            Integer userinfoid = user.getUserinfoid();
            if(userinfoid == null){
                userInfo.setUserRealInfo(null);
            }else{
                QueryWrapper<UserRealInfo> userRealInfoQueryWrapper = new QueryWrapper<>();
                userRealInfoQueryWrapper.eq("id",userinfoid);
                List<UserRealInfo> userRealInfos = userRealInfoMapper.selectList(userRealInfoQueryWrapper);
                if(userRealInfos.size()==0){
                    userInfo.setUserRealInfo(null);
                }else {
                    UserRealInfo userRealInfo = userRealInfos.get(0);
                    userInfo.setUserRealInfo(userRealInfo);
                }
            }
            userInfoList.add(userInfo);
        }
        return new ResponseResult(userInfoList);
    }

    @Override
    public ResponseResult getOneUserAllInfo(Integer id) {
        UserInfo userInfo = new UserInfo();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",id);
        List<User> users = userMapper.selectList(userQueryWrapper);
        if(users.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        User user = users.get(0);
        userInfo.setUser(user);
        Integer userinfoid = user.getUserinfoid();
        if(userinfoid==null){
            return new ResponseResult(userInfo);
        }
        else{
            ResponseResult userRealInfoResponseResult = userRealInfoService.selectUserRealInfo(userinfoid);
            UserRealInfo userRealInfo = (UserRealInfo) userRealInfoResponseResult.getData();
            userInfo.setUserRealInfo(userRealInfo);
        }
        return new ResponseResult(userInfo);

    }


}
