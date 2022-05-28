package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farm;
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
public interface IFarmService extends IService<Farm> {
    int updateFarm(Farm farm);
    int deleteFarm(Integer id);
    int insertFarm(Farm farm);
    ResponseResult selectFarm(Integer id);
}
