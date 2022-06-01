package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderProduct;
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
public interface IOrderProductService extends IService<OrderProduct> {
    int updateOrderProduct(OrderProduct orderProduct);
    int deleteOrderProduct(Integer id);
    int insertOrderProduct(OrderProduct orderProduct);
    ResponseResult selectOrderProduct(Integer id);
    ResponseResult selectOrderProductByOrderId(Integer orderId);
}
