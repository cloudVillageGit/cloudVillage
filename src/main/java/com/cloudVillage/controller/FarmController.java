package com.cloudVillage.controller;


import com.cloudVillage.entity.Farm;
import com.cloudVillage.mapper.FarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public List<Farm> farmList(){
        List<Farm> farms = farmMapper.selectList(null);
        return farms;
    }
}
