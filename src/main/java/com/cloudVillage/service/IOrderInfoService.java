package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderInfo;
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
public interface IOrderInfoService extends IService<OrderInfo> {
    public ResponseResult selectOrderInfoAll(Integer id);
    public ResponseResult selectAllOrderInfoAll();
    int updateOrder(OrderInfo orderInfo);
    int deleteOrder(Integer id);
    int insertOrder(OrderInfo orderInfo);
}
