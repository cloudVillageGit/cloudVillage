package com.cloudVillage.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.mapper.FarmMapper;
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
    private FarmMapper farmMapper;

    @GetMapping("hefa")
    public ResponseResult farmList(){
        JSONObject jsonObject = new JSONObject();
        List<Farm> farms = farmMapper.selectList(null);
        jsonObject.put("hello","findOne");

        jsonObject.append("farmList",farms);

        return new ResponseResult(200,"查询成功",jsonObject);
    }
    @GetMapping("he/{id}")
    public ResponseResult farm(@PathVariable Integer id){
        Farm farm = farmMapper.selectById(id);
        return new ResponseResult(200,"hello",farm);
    }
    @PostMapping("update")
    public ResponseResult farmUpdate(@RequestParam String profiles,@RequestParam Integer rank){
        String str = "helloworld";
        Farm farm = farmMapper.selectById(1);
        farm.setProfiles(profiles);
        farmMapper.updateById(farm);
        return new ResponseResult(200,"操作成功");
    }
}
