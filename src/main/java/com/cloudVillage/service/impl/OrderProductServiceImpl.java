package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.*;
import com.cloudVillage.entity.crossResult.OrderProductDetail;
import com.cloudVillage.mapper.EvaluateMapper;
import com.cloudVillage.mapper.OrderInfoMapper;
import com.cloudVillage.mapper.OrderProductMapper;
import com.cloudVillage.mapper.ProductMapper;
import com.cloudVillage.service.IEvaluateService;
import com.cloudVillage.service.IOrderInfoService;
import com.cloudVillage.service.IOrderProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudVillage.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public int updateOrderProduct(OrderProduct orderProduct) {
        UpdateWrapper<OrderProduct> orderProductUpdateWrapper = new UpdateWrapper<>();
        orderProductUpdateWrapper.eq("id",orderProduct.getId());
        int update = orderProductMapper.update(orderProduct, orderProductUpdateWrapper);
        return update;
    }

    @Override
    public int deleteOrderProduct(Integer id) {
        QueryWrapper<OrderProduct> orderProductQueryWrapper = new QueryWrapper<>();
        orderProductQueryWrapper.eq("id",id);
        List<OrderProduct> orderProducts = orderProductMapper.selectList(orderProductQueryWrapper);
        if(orderProducts.size() == 0){
            return -1;
        }
        int delete = orderProductMapper.delete(orderProductQueryWrapper);
        return delete;
    }

    @Override
    public int insertOrderProduct(OrderProduct orderProduct) {
        int insert = orderProductMapper.insert(orderProduct);
        return insert;
    }

    @Override
    public ResponseResult selectOrderProduct(Integer id) {
        QueryWrapper<OrderProduct> orderProductQueryWrapper = new QueryWrapper<>();
        orderProductQueryWrapper.eq("id",id);
        List<OrderProduct> orderProducts = orderProductMapper.selectList(orderProductQueryWrapper);
        if(orderProducts.size() == 0){
            return new ResponseResult(500,"查询错误");
        }

        OrderProduct orderProduct = orderProducts.get(0);

        Integer _id = orderProduct.getId();
        Integer evaluateid = orderProduct.getEvaluateid();
        Integer productid = orderProduct.getProductid();
        Integer orderid = orderProduct.getOrderid();

        Evaluate evaluate = evaluateMapper.selectById(evaluateid);
        Product product = productMapper.selectById(productid);
        OrderInfo orderInfo = orderInfoMapper.selectById(orderid);

        OrderProductDetail orderProductDetail = new OrderProductDetail();

        orderProductDetail.setEvaluate(evaluate);
        orderProductDetail.setOrderInfo(orderInfo);
        orderProductDetail.setProduct(product);

        return new ResponseResult(200,"查询成功",orderProductDetail);

    }
}
