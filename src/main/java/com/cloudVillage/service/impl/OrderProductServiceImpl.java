package com.cloudVillage.service.impl;

import com.cloudVillage.entity.OrderProduct;
import com.cloudVillage.mapper.OrderProductMapper;
import com.cloudVillage.service.IOrderProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements IOrderProductService {

}
