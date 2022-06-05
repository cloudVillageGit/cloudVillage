package com.cloudVillage.controller;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.OrderTrackMedium;
import com.cloudVillage.mapper.OrderTrackMediumMapper;
import com.cloudVillage.service.IOrderTrackMediumService;
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
@RequestMapping("/orderTrackMedium")
public class OrderTrackMediumController {
    @Autowired
    private IOrderTrackMediumService orderTrackMediumService;

    @PostMapping("otmInsert")
    public ResponseResult otmInsert(@RequestParam Integer otId,@RequestParam String workImg){
        ResponseResult insert = orderTrackMediumService.insert(otId, workImg);
        if(insert.getCode() == 200){
            return new ResponseResult(200,"添加成功",insert.getData());
        }else {
            return new ResponseResult(500, "添加失败");
        }
    }

    @GetMapping("selectById/{id}")
    public ResponseResult selectById(@PathVariable Integer id){
        ResponseResult select = orderTrackMediumService.select(id);
        if(select.getCode()==200){
            return new ResponseResult(200,"查找成功",select.getData());
        }else{
            return new ResponseResult(500,"查找失败");
        }
    }

    @DeleteMapping("deleteById")
    public ResponseResult deleteById(@RequestParam Integer id){
        ResponseResult delete = orderTrackMediumService.delete(id);
        if(delete.getCode() == 200){
            return new ResponseResult(200,"删除成功");
        }else {
            return new ResponseResult(500, "删除失败");
        }
    }

    @DeleteMapping("deleteByOtId")
    public ResponseResult deleteByOtId(@RequestParam Integer otId){
        ResponseResult delete = orderTrackMediumService.deleteByOtId(otId);
        if(delete.getCode() == 200){
            return new ResponseResult(200,"删除成功");
        }else {
            return new ResponseResult(500, "删除失败");
        }
    }

    @GetMapping("selectByOtId/{otId}")
    public ResponseResult selectByOtId(@PathVariable Integer otId){
        ResponseResult selectByOtId = orderTrackMediumService.selectByOtId(otId);
        if(selectByOtId.getCode() == 200){
            return new ResponseResult(200,"otId查找成功",selectByOtId.getData());
        }else {
            return new ResponseResult(500,"otId查找失败");
        }
    }
}
