package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrack;
import com.cloudVillage.entity.OrderTrackSmall;
import com.cloudVillage.mapper.OrderTrackSmallMapper;
import com.cloudVillage.service.IOrderTrackSmallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
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
public class OrderTrackSmallServiceImpl extends ServiceImpl<OrderTrackSmallMapper, OrderTrackSmall> implements IOrderTrackSmallService {

    @Autowired
    private OrderTrackSmallMapper orderTrackSmallMapper;

    @Override
    public ResponseResult insert(String headTitle, String subTitle, Integer otmId) {
        OrderTrackSmall orderTrackSmall = new OrderTrackSmall();
        orderTrackSmall.setHeadtitle(headTitle);
        orderTrackSmall.setSubtitle(subTitle);
        orderTrackSmall.setOrdtramedid(otmId);
        int insert = orderTrackSmallMapper.insert(orderTrackSmall);
        if(insert==1){
            return new ResponseResult(200,"插入成功");
        }else{
            return new ResponseResult(500,"插入失败");
        }
    }

    @Override
    public ResponseResult delete(Integer id) {
        int delete = orderTrackSmallMapper.deleteById(id);
        if(delete==1){
            return new ResponseResult(200,"删除成功");
        }else{
            return new ResponseResult(500,"删除失败");
        }
    }

    @Override
    public ResponseResult select(Integer id) {
        QueryWrapper<OrderTrackSmall> orderTrackSmallQueryWrapper = new QueryWrapper<>();
        orderTrackSmallQueryWrapper.eq("id",id);
        List<OrderTrackSmall> orderTrackSmalls = orderTrackSmallMapper.selectList(orderTrackSmallQueryWrapper);
        if(orderTrackSmalls.size()==0){
            return new ResponseResult(500,"查询失败");
        }else{
            return new ResponseResult(200,"查询成功",orderTrackSmalls.get(0));
        }
    }

    @Override
    public ResponseResult selectByOtmId(Integer otmId) {
        QueryWrapper<OrderTrackSmall> orderTrackSmallQueryWrapper = new QueryWrapper<>();
        orderTrackSmallQueryWrapper.eq("ordtramedId",otmId);
        List<OrderTrackSmall> orderTrackSmalls = orderTrackSmallMapper.selectList(orderTrackSmallQueryWrapper);
        if(orderTrackSmalls.size()==0){
            return new ResponseResult(500,"查询失败");
        }else{
            return new ResponseResult(200,"查询成功",orderTrackSmalls);
        }
    }

    @Override
    public ResponseResult deleteByOtmId(Integer otmId) {
        ResponseResult responseResult = this.selectByOtmId(otmId);
        if(responseResult.getCode() == 200){
            List<OrderTrackSmall> orderSmall = (List<OrderTrackSmall>) responseResult.getData();
//            int delete = orderTrackSmallMapper.deleteById(orderSmall.getId());
            List<Integer> orderSmallIdList = new ArrayList<>();
            for (int i = 0; i < orderSmall.size(); i++) {
                OrderTrackSmall orderTrackSmall = orderSmall.get(i);
                orderSmallIdList.add(orderTrackSmall.getId());
            }
            int delete = orderTrackSmallMapper.deleteBatchIds(orderSmallIdList);

            if(delete >= 1){
                return new ResponseResult(200,"根据ordtramedId删除成功");
            }else{
                return new ResponseResult(500,"根据ordtramedId删除失败");
            }
        }else{
            return new ResponseResult(500,"根据ordtramedId未查询到");
        }
    }
}
