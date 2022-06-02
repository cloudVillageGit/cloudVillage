package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrackMedium;
import com.cloudVillage.entity.OrderTrackSmall;
import com.cloudVillage.entity.crossResult.OrderTrackMS;
import com.cloudVillage.mapper.OrderTrackMapper;
import com.cloudVillage.mapper.OrderTrackMediumMapper;
import com.cloudVillage.service.IOrderTrackMediumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudVillage.service.IOrderTrackService;
import com.cloudVillage.service.IOrderTrackSmallService;
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
public class OrderTrackMediumServiceImpl extends ServiceImpl<OrderTrackMediumMapper, OrderTrackMedium> implements IOrderTrackMediumService {

    @Autowired
    private OrderTrackMediumMapper orderTrackMediumMapper;
    @Autowired
    private IOrderTrackSmallService orderTrackSmallService;
//    @Autowired
//    private OrderTrackMediumMapper orderTrackMediumMapper;

    @Override
    public ResponseResult insert(Integer otId, String workImg) {
        OrderTrackMedium orderTrackMedium = new OrderTrackMedium();
        orderTrackMedium.setWorkimg(workImg);
        orderTrackMedium.setOrdtraid(otId);
        int insert = orderTrackMediumMapper.insert(orderTrackMedium);
        if(insert==1){
            return new ResponseResult(200,"插入成功",orderTrackMedium.getId());
        }else{
            return new ResponseResult(500,"插入失败");
        }
    }

    @Override
    public ResponseResult delete(Integer id) {
        int delete = orderTrackMediumMapper.deleteById(id);
        if(delete==1){
            orderTrackSmallService.deleteByOtmId(id);
            return new ResponseResult(200,"删除成功");
        }else{
            return new ResponseResult(500,"删除失败");
        }
    }

    @Override
    public ResponseResult select(Integer id) {
        OrderTrackMS orderTrackMS = new OrderTrackMS();
        QueryWrapper<OrderTrackMedium> orderTrackMediumQueryWrapper = new QueryWrapper<>();
        orderTrackMediumQueryWrapper.eq("id",id);
        List<OrderTrackMedium> orderTrackMedia = orderTrackMediumMapper.selectList(orderTrackMediumQueryWrapper);
        if(orderTrackMedia.size()==0){
            return new ResponseResult(500,"查询失败");
        }else{
            OrderTrackMedium orderTrackMedium = orderTrackMedia.get(0);
            Integer otmId = orderTrackMedium.getId();
            ResponseResult responseResult = orderTrackSmallService.selectByOtmId(otmId);
            if(responseResult.getCode()==200){
                List<OrderTrackSmall> orderTrackSmallList = (List<OrderTrackSmall>) responseResult.getData();
                orderTrackMS.setOrderTrackSmallList(orderTrackSmallList);

            }else{
                orderTrackMS.setOrderTrackSmallList(null);
            }
            orderTrackMS.setOrderTrackMedium(orderTrackMedium);
            return new ResponseResult(200,"查询成功",orderTrackMS);
        }

    }

    @Override
    public ResponseResult selectByOtId(Integer otId) {

        List<OrderTrackMS> orderTrackMSList = new ArrayList<>();
        
        QueryWrapper<OrderTrackMedium> orderTrackMediumQueryWrapper = new QueryWrapper<>();
        orderTrackMediumQueryWrapper.eq("ordtraId",otId);
        List<OrderTrackMedium> orderTrackMedia = orderTrackMediumMapper.selectList(orderTrackMediumQueryWrapper);
        if(orderTrackMedia.size()==0){
            return new ResponseResult(500,"查询失败");
        }else{
            List<OrderTrackMedium> orderTrackMediumList = orderTrackMedia;

            for (int i = 0; i < orderTrackMediumList.size(); i++) {
                OrderTrackMS orderTrackMS = new OrderTrackMS();

                OrderTrackMedium orderTrackMedium = orderTrackMediumList.get(i);
                Integer otmId = orderTrackMedium.getId();
                ResponseResult responseResult = orderTrackSmallService.selectByOtmId(otmId);
                if(responseResult.getCode()==200){
                    List<OrderTrackSmall> orderTrackSmallList = (List<OrderTrackSmall>) responseResult.getData();
                    orderTrackMS.setOrderTrackSmallList(orderTrackSmallList);
                }else{
                    orderTrackMS.setOrderTrackSmallList(null);
                }
                orderTrackMS.setOrderTrackMedium(orderTrackMedium);
                orderTrackMSList.add(orderTrackMS);
            }
            return new ResponseResult(200,"查询成功",orderTrackMSList);
            
        }
    }

    @Override
    public ResponseResult deleteByOtId(Integer otId) {
        OrderTrackMS orderTrackMS = new OrderTrackMS();
        QueryWrapper<OrderTrackMedium> orderTrackMediumQueryWrapper = new QueryWrapper<>();
        orderTrackMediumQueryWrapper.eq("ordtraId",otId);
        List<OrderTrackMedium> orderTrackMedia = orderTrackMediumMapper.selectList(orderTrackMediumQueryWrapper);
        if(orderTrackMedia.size()==0){
            return new ResponseResult(500,"查询失败");
        }else {
            List<Integer> orderTrackMediaId = new ArrayList<>();
            for (int i = 0; i < orderTrackMedia.size(); i++) {
                OrderTrackMedium orderTrackMedium = orderTrackMedia.get(i);
                Integer otmId = orderTrackMedium.getId();
                orderTrackMediaId.add(otmId);
                orderTrackSmallService.deleteByOtmId(otmId);
            }
            int delete = orderTrackMediumMapper.deleteBatchIds(orderTrackMediaId);
            if(delete >= 1){
                return new ResponseResult(200,"删除成功");
            }else{
                return new ResponseResult(500,"删除失败");
            }
        }
    }
}
