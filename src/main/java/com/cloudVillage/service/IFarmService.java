package com.cloudVillage.service;

import com.cloudVillage.entity.Farm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IFarmService extends IService<Farm> {
    void selectAll();
}
