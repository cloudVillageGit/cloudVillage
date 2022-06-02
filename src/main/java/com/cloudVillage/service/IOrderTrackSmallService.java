package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrackSmall;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IOrderTrackSmallService extends IService<OrderTrackSmall> {
    public ResponseResult insert(String headTitle,String subTitle,Integer otmId);
    public ResponseResult delete(Integer id);
    public ResponseResult select(Integer id);

    public ResponseResult selectByOtmId(Integer otmId);
    public ResponseResult deleteByOtmId(Integer otmId);
}
