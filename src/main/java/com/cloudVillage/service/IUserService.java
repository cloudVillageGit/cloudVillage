package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.xml.ws.Response;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IUserService extends IService<User> {
    ResponseResult<User> login(String openId, String userToken,String avatarUrl,String nickName);
    int checkToken(String userToken);
    int updateUserInfo(User user);
    ResponseResult deleteUser(Integer id);
    ResponseResult getAllUserAllInfo();
    ResponseResult getOneUserAllInfo(Integer id);
}
