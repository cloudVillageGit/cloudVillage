package com.cloudVillage.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.mapper.FarmMapper;
import com.cloudVillage.service.IFarmService;
import com.cloudVillage.service.IFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/farm")
public class FarmController {
    @Autowired
    private IFarmService farmService;

    /**
     * 更新农场信息
     * @param farm
     * @return
     */
    @PutMapping("updateFarm")
    public ResponseResult updateFarm(@RequestBody Farm farm){
        int update = farmService.updateFarm(farm);
        if(update==0){
            return new ResponseResult(500,"更新农场信息失败");
        }else{
            return new ResponseResult(200,"更新农场信息成功");
        }
    }

    /**
     * 删除农场信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteFarm")
    public ResponseResult deleteFarm(@RequestParam Integer id){
        int deleteFarm = farmService.deleteFarm(id);
        if(deleteFarm==1){
            return new ResponseResult(200,"删除农场成功");
        }else if(deleteFarm==-1){
            return new ResponseResult(500,"需要删除的农场不存在");
        }else {
            return new ResponseResult(500,"删除农场失败");
        }
    }

    /**
     * 通过id获取农场信息
     * @param id
     * @return
     */
    @GetMapping("selectFarm/{id}")
    public ResponseResult selectFarm(@PathVariable Integer id){
        ResponseResult selectFarm = farmService.selectFarm(id);
        if(selectFarm.getCode()==null){
            return new ResponseResult(200,"查询农场信息成功",selectFarm);
        }else{
            return new ResponseResult(500,"查询农场信息失败");
        }
    }

    /**
     * 插入农场信息
     * @param farm
     * @return
     */
    @PostMapping("insertFarm")
    public ResponseResult insertFarm(@RequestBody Farm farm){
        int insertFarm = farmService.insertFarm(farm);
        if(insertFarm == 1){
            return new ResponseResult(200,"插入农场成功");
        }else{
            return new ResponseResult(500,"插入农场失败");
        }
    }
}
