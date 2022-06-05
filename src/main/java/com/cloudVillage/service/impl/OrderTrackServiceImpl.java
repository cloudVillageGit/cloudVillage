package com.cloudVillage.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.Util.CheckObjectIsNullUtils;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrack;
import com.cloudVillage.entity.OrderTrackMedium;
import com.cloudVillage.entity.OrderTrackSmall;
import com.cloudVillage.mapper.OrderTrackMapper;
import com.cloudVillage.mapper.OrderTrackMediumMapper;
import com.cloudVillage.mapper.OrderTrackSmallMapper;
import com.cloudVillage.service.IOrderTrackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单追踪表 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class OrderTrackServiceImpl extends ServiceImpl<OrderTrackMapper, OrderTrack> implements IOrderTrackService {

    @Autowired
    private OrderTrackMapper orderTrackMapper;
    @Autowired
    private OrderTrackMediumMapper orderTrackMediumMapper;
    @Autowired
    private OrderTrackSmallMapper orderTrackSmallMapper;

    /**
     * 订单追踪的大小中表关联
     * @param id
     * @return
     */
    @Override
    public ResponseResult orderTrackAllInfo(Integer id) {
        JSONObject jsonObjectNote = new JSONObject();
        // 查找大表
        OrderTrack orderTrack = orderTrackMapper.selectById(id);
        QueryWrapper<OrderTrack> orderTrackQueryWrapper= new QueryWrapper<>();

        orderTrackQueryWrapper.eq("id",id);
        List<OrderTrack> orderTracks = orderTrackMapper.selectList(orderTrackQueryWrapper);
        // 判断对象是否为空
        if(orderTracks.size()==0){
            return new ResponseResult(500,"大表查询失败，不存在该对象");
        }
        // 判断对象是否为空
//        CheckObjectIsNullUtils checkObjectIsNullUtils = new CheckObjectIsNullUtils();
//        boolean j = checkObjectIsNullUtils.objCheckIsNull(orderTrack);

//        if(!j){
//            return new ResponseResult(500,"大表查询失败，不存在该对象");
//        }

        String monitor = orderTrack.getMonitor();
        String productionNote = orderTrack.getProductionnotecontent();
// json
        jsonObjectNote.put("monitor",monitor);
        jsonObjectNote.put("productionNote",productionNote);

        // 查找大表 对应 中表
        QueryWrapper<OrderTrackMedium> orderTrackMediumQueryWrapper = new QueryWrapper<>();
        // 选取"workTime","workImg" 两个字段的值
        orderTrackMediumQueryWrapper.select("id","workTime","workImg");
        orderTrackMediumQueryWrapper.eq("ordtraId",id);
        // 查找对应中表
        List<OrderTrackMedium> orderTrackMediaList = orderTrackMediumMapper.selectList(orderTrackMediumQueryWrapper);
        OrderTrackMedium orderTrackMedium = new OrderTrackMedium();


        List<JSONObject> jsonObjectList = new ArrayList<>();


        if (orderTrackMediaList.size() != 0) {

            for (int i = 0; i < orderTrackMediaList.size(); i++) {
                // json
                JSONObject productionNoteArrayOne = new JSONObject();

                orderTrackMedium = orderTrackMediaList.get(i);
                // json
                productionNoteArrayOne.put("id",id);
                productionNoteArrayOne.put("noteTime",orderTrackMedium.getWorktime());
                productionNoteArrayOne.put("workImg",orderTrackMedium.getWorkimg());

                Integer mediumId = orderTrackMedium.getId();
                QueryWrapper<OrderTrackSmall> orderTrackSmallQueryWrapper = new QueryWrapper<>();
                orderTrackSmallQueryWrapper.select("headTitle","subTitle");
                orderTrackSmallQueryWrapper.eq("ordtramedId",mediumId);
                List<OrderTrackSmall> orderTrackSmallsList = orderTrackSmallMapper.selectList(orderTrackSmallQueryWrapper);

                if(orderTrackSmallsList.size()==0){
                    return new ResponseResult(500,"对应小表查询错误");
                }

                System.out.println(orderTrackSmallsList.toString());
                OrderTrackSmall orderTrackSmall = new OrderTrackSmall();

                //json
                JSONArray workArray = JSONUtil.parseArray(orderTrackSmallsList);

                // json
                productionNoteArrayOne.put("workArray",workArray);

                // jsonArray
                jsonObjectList.add(productionNoteArrayOne);
                JSONArray productNoteArray = JSONUtil.parseArray(jsonObjectList);

                System.out.println(productNoteArray);
                jsonObjectNote.put("productionNoteArray",productNoteArray);
                System.out.println("jsonNoteobj:" + jsonObjectNote);
            }
        }else{
            // 没找到响应数据，返回错误！
            return new ResponseResult(500,"对应中表查询错误");
        }
        System.out.println(jsonObjectNote);

//        return null;
        return new ResponseResult(jsonObjectNote);
    }

    @Override
    public ResponseResult orderTrackByProductId(Integer productId) {
        QueryWrapper<OrderTrack> orderTrackQueryWrapper= new QueryWrapper<>();
        orderTrackQueryWrapper.eq("productId",productId);
        List<OrderTrack> orderTracks = orderTrackMapper.selectList(orderTrackQueryWrapper);
        if(orderTracks.size()== 0){
            return new ResponseResult(500,"查询失败");
        }else{
            return new ResponseResult(200,"查询成功",orderTracks.get(0));
        }
    }

}
