package com.cloudVillage.controller;

import com.cloudVillage.entity.OrderTrackMedium;
import com.cloudVillage.mapper.OrderTrackMediumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/orderTrackMedium")
public class OrderTrackMediumController {
    @Autowired
    private OrderTrackMediumMapper orderTrackMediumMapper;


    @PostMapping("/hrtr")
    public void InsertA(){
        OrderTrackMedium orderTrackMedium = new OrderTrackMedium();
        orderTrackMedium.setOrdtraid(1);
        orderTrackMediumMapper.insert(orderTrackMedium);
    }
}
