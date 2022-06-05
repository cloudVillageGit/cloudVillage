package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrack;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单追踪表 服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IOrderTrackService extends IService<OrderTrack> {
    /**
     * 订单追踪所有信息 大表关联中表关联小表
     */
    public ResponseResult orderTrackAllInfo(Integer id);
    public ResponseResult orderTrackByProductId(Integer productId);
}
