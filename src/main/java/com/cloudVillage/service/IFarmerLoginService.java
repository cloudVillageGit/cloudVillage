package com.cloudVillage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.FarmerLogin;
import com.cloudVillage.entity.UserRealInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/14:56
 * @Description:
 */
public interface IFarmerLoginService extends IService<FarmerLogin> {
    int updateFarmerLogin(FarmerLogin farmerLogin);
    int deleteFarmerLogin(Integer id);
    int insertFarmerLogin(FarmerLogin farmerLogin);
    ResponseResult selectFarmerLogin(String name,String password);
}
