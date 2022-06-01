package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderProduct;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {
    @Autowired
    private IOrderProductService orderProductService;

    /**
     * 修改订单_产品信息
     */
    @PutMapping("updateOrderAndProduct")
    public ResponseResult updateOrder(@RequestBody OrderProduct orderProduct){
        int update = orderProductService.updateOrderProduct(orderProduct);
        if(update==0){
            return new ResponseResult(500,"更新用户真实信息失败");
        }else{
            return new ResponseResult(200,"更新用户真实信息成功");
        }
    }

    /**
     * 删除订单_产品信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteOrderAndProduct")
    public ResponseResult deleteUserInfo(@RequestParam Integer id){
        int delete = orderProductService.deleteOrderProduct(id);
        if(delete==1){
            return new ResponseResult(200,"删除订单_产品关系成功");
        }else if(delete==0){
            return new ResponseResult(500,"删除订单_产品关系失败");
        }else {
            return new ResponseResult(500,"订单_产品关系已逻辑删除");
        }
    }

    /**
     * 插入订单——产品真实信息
     * @param orderProduct
     * @return
     */
    @PostMapping("insertOrderAndProduct")
    public ResponseResult insertUserRealInfo(@RequestBody OrderProduct orderProduct){
        int insert = orderProductService.insertOrderProduct(orderProduct);
        if(insert == 1){
            return new ResponseResult(200,"插入订单_产品关系成功");
        }else{
            return new ResponseResult(500,"插入订单_产品关系失败");
        }
    }

    /**
     * 通过id获取用户真实信息
     * @param id
     * @return
     */
    @GetMapping("selectOrderAndProduct/{id}")
    public ResponseResult selectOrderAndProduct(@PathVariable Integer id){
        ResponseResult selectOrderAndProduct = orderProductService.selectOrderProduct(id);
//        if(selectOrderAndProduct.getCode()==null){
//            return new ResponseResult(200,"查询订单_产品关系成功",selectOrderAndProduct);
//        }else{
//            return new ResponseResult(500,"查询订单_产品关系失败");
//        }
        return new ResponseResult(200,"查询订单_产品关系成功",selectOrderAndProduct);

    }
}
