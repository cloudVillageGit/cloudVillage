package com.cloudVillage.controller;


import com.cloudVillage.Util.PageListUtil;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.Product;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 初步查询，返回结果至查询结果界面，有基本信息，及图片信息
     * @param keyWord
     * @return
     */
    @GetMapping("searchResult/{keyWord}")
    public ResponseResult SearchResponse(@PathVariable String keyWord){
        ResponseResult searchResultList = productService.searchProduct(keyWord);
        Integer code = searchResultList.getCode();
        if(code==null)
            return new ResponseResult(200,"查询成功",searchResultList);
        else
            return new ResponseResult(500,"查询失败");
    }

    /**
     * 详细信息
     * 农产品关联表的所有信息，包括图片，分类，农场，农产品属性的全部
     * json: mainUrl:主要图片（5张
     *         otherUrl:其他图片
     */
    @GetMapping("searchDetail/{id}")
    public ResponseResult SearchDetail(@PathVariable Integer id){
        ResponseResult searchDetailList = productService.productDetail(id);
        Integer code = searchDetailList.getCode();
        if(code==null)
            return new ResponseResult(200,"查询成功",searchDetailList);
        else
            return new ResponseResult(500,"查询失败");
    }

    /**
     * 修改农产品信息
     */
    @PutMapping("updateProduct")
    public ResponseResult updateProduct(@RequestBody Product product){
        int update = productService.updateProduct(product);
        if(update==0){
            return new ResponseResult(500,"更新农产品信息失败");
        }else{
            return new ResponseResult(200,"更新农产品信息成功");
        }
    }

    /**
     * 删除农产品信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteProduct")
    public ResponseResult deleteProduct(@RequestParam Integer id){
        int deleteProduct = productService.deleteProduct(id);
        if(deleteProduct==1){
            return new ResponseResult(200,"删除农产品成功");
        }else if(deleteProduct==0){
            return new ResponseResult(500,"删除农产品失败");
        }else {
            return new ResponseResult(500,"农产品已逻辑删除");
        }
    }
    /**
     * 插入产品信息
     * @param product
     * @return
     */
    @PostMapping("insertProduct")
    public ResponseResult insertProduct(@RequestBody Product product){
        int insertProduct = productService.insertProduct(product);
        if(insertProduct == 1){
            return new ResponseResult(200,"插入农产品成功");
        }else{
            return new ResponseResult(500,"插入农产品失败");
        }
    }


    /**
     * 后台部分
     *  foreground-background environment  ->  FBE
     */
//    @PostMapping("selectProductsFBE")
//    public ResponseResult selectProductsFBE(@RequestParam Integer farmId,
//                                            @RequestParam(required = false) String keyword,
//                                            @RequestParam(required = false,defaultValue = "1")Integer currentPage,
//                                            @RequestParam(required = false,defaultValue = "3" )Integer pageSize){
//
//        List<ProductDetail> products = (List<ProductDetail>) productService.selectByFarmFBE(farmId, keyword).getData();
//
//        PageListUtil pageListUtil = new PageListUtil();
//        com.baomidou.mybatisplus.extension.plugins.pagination.Page pages = pageListUtil.getPages(currentPage, pageSize, products);
//
//        return new ResponseResult(200,"查询成功",pages.getRecords());
//    }


}
