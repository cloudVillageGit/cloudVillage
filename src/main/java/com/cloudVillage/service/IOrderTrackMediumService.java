package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrackMedium;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IOrderTrackMediumService extends IService<OrderTrackMedium> {
    public ResponseResult insert(Integer otId,String workImg);
    public ResponseResult delete(Integer id);
    public ResponseResult select(Integer id);

    public ResponseResult selectByOtId(Integer otId);
    public ResponseResult deleteByOtId(Integer otId);
}
