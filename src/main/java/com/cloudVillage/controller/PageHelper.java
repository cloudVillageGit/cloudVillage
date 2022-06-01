package com.cloudVillage.controller;

import cn.hutool.json.JSONObject;
import com.cloudVillage.Util.PageListUtil;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.crossResult.OrderDetail;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.service.IOrderInfoService;
import com.cloudVillage.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/28/22:16
 * @Description:
 */
@RestController
@RequestMapping("/page")
public class PageHelper {
    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 后台部分
     *  foreground-background environment  ->  FBE
     */
    @PostMapping("selectProductsFBE")
    public ResponseResult selectProductsFBE(@RequestParam Integer farmId,
                                            @RequestParam(required = false) String keyword,
                                            @RequestParam(required = false,defaultValue = "1")Integer currentPage,
                                            @RequestParam(required = false,defaultValue = "3" )Integer pageSize){

        List<ProductDetail> products = (List<ProductDetail>) productService.selectByFarmFBE(farmId, keyword).getData();

        PageListUtil pageListUtil = new PageListUtil();
        Page pages = pageListUtil.getPages(currentPage, pageSize, products);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",pages.getRecords());
        jsonObject.put("total",products.size());
        return new ResponseResult(200,"查询成功",jsonObject);
    }


    @PostMapping("selectOrderFBE")
    public ResponseResult selectOrdersFBE(@RequestParam(required = false) Integer farmId,
                                            @RequestParam(required = false) String number,
                                            @RequestParam(required = false,defaultValue = "1")Integer currentPage,
                                            @RequestParam(required = false,defaultValue = "3" )Integer pageSize){

        List<OrderDetail> orderDetails = (List<OrderDetail>) orderInfoService.selectByFarmSetByUserAndNumberFBE(farmId, number).getData();

        PageListUtil pageListUtil = new PageListUtil();
        Page pages = pageListUtil.getPages(currentPage, pageSize, orderDetails);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",pages.getRecords());
        jsonObject.put("total",orderDetails.size());

        return new ResponseResult(200,"查询成功",jsonObject);

    }
}
