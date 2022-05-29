package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.*;
import com.cloudVillage.entity.crossResult.OrderDetail;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.entity.crossResult.UserInfo;
import com.cloudVillage.mapper.OrderInfoMapper;
import com.cloudVillage.service.IAddressService;
import com.cloudVillage.service.IFarmService;
import com.cloudVillage.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudVillage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        System.out.println(oneUserAllInfo);
        if(oneUserAllInfo.getCode()==null||oneUserAllInfo.getCode()==500){
            orderDetail.setUserInfo(null);
        }else {
            UserInfo userInfo = (UserInfo) oneUserAllInfo.getData();
            orderDetail.setUserInfo(userInfo);
        }

        ResponseResult selectFarm = farmService.selectFarm(farmid);
        if(selectFarm.getCode()==null||selectFarm.getCode()==500) {
            orderDetail.setFarm(null);
        }else{
            Farm farm = (Farm) selectFarm.getData();
            orderDetail.setFarm(farm);
        }

        ResponseResult selectAddress = addressService.selectAddress(addressid);
        if(selectAddress.getCode()==null||selectAddress.getCode()==500){
            orderDetail.setAddress(null);
        }else{
            Address address = (Address) selectAddress.getData();
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
        int delete = orderInfoMapper.delete(orderInfoQueryWrapper);
        return delete;
    }

    @Override
    public int insertOrder(OrderInfo orderInfo) {
        int insert = orderInfoMapper.insert(orderInfo);
        return insert;
    }

    @Override
    public ResponseResult selectByFarmSetByUserAndNumberFBE(Integer farmId,  String number) {
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq("farmId",farmId);

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
