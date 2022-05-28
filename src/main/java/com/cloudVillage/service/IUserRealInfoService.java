package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.cloudVillage.entity.UserRealInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户真实信息 服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IUserRealInfoService extends IService<UserRealInfo> {
    int updateUserInfo(UserRealInfo userInfo);
    int deleteUserInfo(Integer id);
    int insertUserInfo(UserRealInfo userInfo);
    ResponseResult selectUserRealInfo(Integer id);
}
