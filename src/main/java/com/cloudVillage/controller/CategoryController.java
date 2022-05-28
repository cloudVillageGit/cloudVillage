package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 农产品分类表 前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 按小程序格式指定返回json
     * @param keyWord
     * @return
     */
    @GetMapping("menu/{keyWord}")
    public ResponseResult returnMenu(@PathVariable String keyWord){
        ResponseResult menu = categoryService.CategoryList(keyWord);
        return new ResponseResult(200,"查询分类成功",menu);
    }
}
