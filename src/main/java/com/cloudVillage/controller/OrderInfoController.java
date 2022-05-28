package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderInfo;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 查询所有订单的所有数据
     * @return
     */
    @GetMapping("selectAllOrder")
    public ResponseResult selectAllOrderInfoAll(){
        ResponseResult orderInfoAll = orderInfoService.selectAllOrderInfoAll();
        if(orderInfoAll.getCode()==null){
            return new ResponseResult(200,"查询订单信息成功",orderInfoAll);
        }else{
            return new ResponseResult(500,"查询失败");
        }
    }

    /**
     * 查询单个订单表格的所有信息
     * 包括：用户信息，农场信息，收货地址信息
     * @param id
     * @return
     */
    @GetMapping("selectOrderInfoAll/{id}")
    public ResponseResult selectOrderInfoAll(@PathVariable Integer id){
        ResponseResult orderInfoOne = orderInfoService.selectOrderInfoAll(id);
        if(orderInfoOne.getCode()==null){
            return new ResponseResult(200,"查询订单信息成功",orderInfoOne);
        }else{
            return new ResponseResult(500,"查询失败");
        }

    }


    /**
     * 删除订单信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteOrder")
    public ResponseResult deleteOrder(@RequestParam Integer id){
        int deleteUserInfo = orderInfoService.deleteOrder(id);
        if(deleteUserInfo==1){
            return new ResponseResult(200,"删除订单成功");
        }else if(deleteUserInfo==0){
            return new ResponseResult(500,"删除订单失败");
        }else {
            return new ResponseResult(500,"订单已逻辑删除");
        }
    }

    /**
     * 修改订单信息
     */
    @PutMapping("updateOrder")
    public ResponseResult updateOrder(@RequestBody OrderInfo orderInfo){
        int update = orderInfoService.updateOrder(orderInfo);
        if(update==0){
            return new ResponseResult(500,"更新订单信息失败");
        }else{
            return new ResponseResult(200,"更新订单信息成功");
        }
    }

    /**
     * 插入订单信息
     * @param orderInfo
     * @return
     */
    @PostMapping("insertUserRealInfo")
    public ResponseResult insertUserRealInfo(@RequestBody OrderInfo orderInfo){
        int insertUserRealInfo = orderInfoService.insertOrder(orderInfo);
        if(insertUserRealInfo == 1){
            return new ResponseResult(200,"插入订单成功");
        }else{
            return new ResponseResult(500,"插入订单失败");
        }
    }

}
