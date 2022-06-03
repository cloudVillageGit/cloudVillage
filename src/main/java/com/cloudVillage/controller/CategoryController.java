package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 通过categoryTop得到二级目录名称,不必要，没有的时候全搜
     * @param keyWord
     * @return
     */
    @PostMapping("categoryTopAndSecondMenu")
    public ResponseResult returnCategoryMenu(@RequestParam(required = false) String keyWord){
        ResponseResult responseResult = categoryService.searchTop(keyWord);
        if(responseResult.getCode() == 200){
            return new ResponseResult(200,"查询成功",responseResult.getData());
        }else{
            return new ResponseResult(500,"查询失败");
        }
    }
    /**
     * 通过二级全搜三级，必要
     */
    @PostMapping("categoryThirdMenu")
    public ResponseResult returnThirdCategory(@RequestParam String keyWord){
        ResponseResult responseResult = categoryService.searchSecond(keyWord);
        if(responseResult.getCode()==200){
            return new ResponseResult(200,"查询成功",responseResult.getData());
        }else{
            return new ResponseResult(500,"查询失败");
        }
    }
}
