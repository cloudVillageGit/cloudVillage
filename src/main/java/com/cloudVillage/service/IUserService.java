package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IUserService extends IService<User> {
    ResponseResult<User> login(String openId, String userToken);
    int checkToken(String userToken);

}
