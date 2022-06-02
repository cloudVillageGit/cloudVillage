package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.*;
import com.cloudVillage.entity.crossResult.OrderDetail;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.entity.crossResult.UserInfo;
import com.cloudVillage.mapper.OrderInfoMapper;
import com.cloudVillage.mapper.OrderProductMapper;
import com.cloudVillage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private IFarmService farmService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private IOrderProductService orderProductService;

    @Override
    public ResponseResult selectOrderInfoAll(Integer id) {

        OrderDetail orderDetail = new OrderDetail();

//        OrderInfo orderInfo = orderInfoMapper.selectById(id);
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq("id",id);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(orderInfoQueryWrapper);
        if(orderInfos.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        OrderInfo orderInfo = orderInfos.get(0);

        Integer userid = orderInfo.getUserid();
        Integer farmid = orderInfo.getFarmid();
        Integer addressid = orderInfo.getAddressid();

        ResponseResult oneUserAllInfo = userService.getOneUserAllInfo(userid);
        UserInfo userInfo = (UserInfo) oneUserAllInfo.getData();
        if(userInfo.getUser().getId()==null){
            orderDetail.setUserInfo(null);
        }else {
            orderDetail.setUserInfo(userInfo);
        }

        ResponseResult selectFarm = farmService.selectFarm(farmid);
        Farm farm = (Farm) selectFarm.getData();
        if(farm.getId()==null) {
            orderDetail.setFarm(null);
        }else{
            orderDetail.setFarm(farm);
        }

        ResponseResult selectAddress = addressService.selectAddress(addressid);
        Address address = (Address) selectAddress.getData();
        if(address.getId()==null){
            orderDetail.setAddress(null);
        }else{
            orderDetail.setAddress(address);
        }
        orderDetail.setOrderInfo(orderInfo);
        return new ResponseResult(orderDetail);
    }

    @Override
    public ResponseResult selectAllOrderInfoAll() {
        List<OrderDetail> list = new ArrayList<>();
        List<OrderInfo> orderInfoList = orderInfoMapper.selectList(null);
        for (int i = 0; i < orderInfoList.size(); i++) {
            OrderInfo orderInfo = orderInfoList.get(i);
            Integer id = orderInfo.getId();
            OrderDetail orderDetail = (OrderDetail) this.selectOrderInfoAll(id).getData();
            list.add(orderDetail);
        }
        return new ResponseResult(list);
    }

    @Override
    public int updateOrder(OrderInfo orderInfo) {
        UpdateWrapper<OrderInfo> orderInfoUpdateWrapper = new UpdateWrapper<>();
        orderInfoUpdateWrapper.eq("id",orderInfo.getId());
        int update = orderInfoMapper.update(orderInfo, orderInfoUpdateWrapper);
        return update;
    }

    @Override
    public int deleteOrder(Integer id) {
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq("id",id);
        List<OrderInfo> orderInfoList = orderInfoMapper.selectList(orderInfoQueryWrapper);
        if(orderInfoList.size() == 0){
            return -1;
        }
        int deleteOrderInfo = orderInfoMapper.delete(orderInfoQueryWrapper);
        QueryWrapper<OrderProduct> orderProductQueryWrapper = new QueryWrapper<>();
        orderProductQueryWrapper.eq("orderId",id);
        List<OrderProduct> orderProducts = orderProductMapper.selectList(orderProductQueryWrapper);
        OrderProduct orderProduct = orderProducts.get(0);
        int deleteOrderProduct = orderProductService.deleteOrderProduct(orderProduct.getId());
        if(deleteOrderInfo==deleteOrderProduct){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public ResponseResult insertOrder(OrderInfo orderInfo) {
        String orderStamp = "td_"+new Date().getTime();
        orderInfo.setOrdernum(orderStamp);
        int insert = orderInfoMapper.insert(orderInfo);
        Integer id = orderInfo.getId();
        if(insert == 1){
            return new ResponseResult(200,"插入成功",orderInfo.getId());
        }else{
            return new ResponseResult(500,"插入失败");
        }
    }

    @Override
    public ResponseResult selectByFarmSetByUserAndNumberFBE(Integer farmId,  String number) {
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        if(farmId == null){
//            orderInfoQueryWrapper
        }else {
            orderInfoQueryWrapper.eq("farmId", farmId);
        }
        List<OrderDetail> orderDetails = new ArrayList<>();

        if(number != null){
            orderInfoQueryWrapper.like("orderNum",number);
        }

        List<OrderInfo> orderInfos = orderInfoMapper.selectList(orderInfoQueryWrapper);

        if(orderInfos.size()==0){
            return new ResponseResult(500,"查询错误,无数据");
        }

        for (int i = 0; i < orderInfos.size(); i++) {
            OrderInfo orderInfo = orderInfos.get(i);
            Integer id = orderInfo.getId();
            ResponseResult responseResult = this.selectOrderInfoAll(id);
            OrderDetail orderDetail = (OrderDetail) responseResult.getData();
            orderDetails.add(orderDetail);
        }
        return new ResponseResult(orderDetails);
    }
}
