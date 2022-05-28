package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farmer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudVillage.entity.UserRealInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IFarmerService extends IService<Farmer> {
    int updateFarmer(Farmer farmer);
    int deleteFarmer(Integer id);
    int insertFarmer(Farmer farmer);
    ResponseResult selectFarmer(Integer id);
    ResponseResult selectFarmerAll(Farmer farmer);
}
