package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.service.IOrderTrackSmallService;
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
@RequestMapping("/orderTrackSmall")
public class OrderTrackSmallController {
    @Autowired
    private IOrderTrackSmallService orderTrackSmallService;

    @PostMapping("otsInsert")
    public ResponseResult otsInsert(@RequestParam String headTitle,
                                    @RequestParam String subTitle,
                                    @RequestParam Integer omtId){
        ResponseResult insert = orderTrackSmallService.insert(headTitle, subTitle, omtId);
        if(insert.getCode() == 200){
            return new ResponseResult(200,"添加成功");
        }else {
            return new ResponseResult(500, "添加失败");
        }
    }

    @DeleteMapping("otsDelete")
    public ResponseResult otsDelete(@RequestParam Integer id){
        ResponseResult delete = orderTrackSmallService.delete(id);
        if(delete.getCode() == 200){
            return new ResponseResult(200,"删除成功");
        }else {
            return new ResponseResult(500, "删除失败");
        }
    }

    @GetMapping("otsSelectById/{id}")
    public ResponseResult otsSelect(@PathVariable Integer id){
        ResponseResult responseResult = orderTrackSmallService.select(id);
        if(responseResult.getCode() == 200){
            return new ResponseResult(200,"查找成功",responseResult.getData());
        }else {
            return new ResponseResult(500,"查找失败");
        }
    }

    @GetMapping("otsSelectByOtmId/{otmId}")
    public ResponseResult otsSelectOtmId(@PathVariable Integer otmId){
        ResponseResult responseResult = orderTrackSmallService.selectByOtmId(otmId);
        if(responseResult.getCode() == 200){
            return new ResponseResult(200,"otmId查找成功",responseResult.getData());
        }else {
            return new ResponseResult(500,"otmId查找失败");
        }
    }

    @DeleteMapping("otsDeleteOtmId")
    public ResponseResult otsDeleteOtmId(@RequestParam Integer otmId){
        ResponseResult delete = orderTrackSmallService.deleteByOtmId(otmId);
        if(delete.getCode() == 200){
            return new ResponseResult(200,"删除成功");
        }else {
            return new ResponseResult(500, "删除失败");
        }
    }
}
