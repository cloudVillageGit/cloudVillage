package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrack;
import com.cloudVillage.mapper.OrderTrackMapper;
import com.cloudVillage.service.IOrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单追踪表 前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/orderTrack")
public class OrderTrackController {
    @Autowired
    private IOrderTrackService orderTrackService;

    /**
     * 获取订单大中小表中的所有数据
     * @param id 大表id
     * @return ResponseResult
     */
    @GetMapping("allTrekInfo/{id}")
    public ResponseResult getAllTrekInfo(@PathVariable Integer id){
        ResponseResult result = orderTrackService.orderTrackAllInfo(id);
        System.out.println(result);
        Integer code = result.getCode();
        System.out.println("code"+code);
        if(code==null){
            return new ResponseResult(200,"查询生产小记成功",result);
        }
        return new ResponseResult(500,"查询生产小记失败");
    }

}
