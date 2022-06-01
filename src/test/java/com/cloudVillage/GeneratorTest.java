package com.cloudVillage;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudVillage.Util.PageListUtil;
import com.cloudVillage.config.QiNiuYunConfig;
import com.cloudVillage.controller.PageHelper;
import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.service.IOrderInfoService;
import com.cloudVillage.service.IProductService;
import com.cloudVillage.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GeneratorTest {
//    @Autowired
//    private FarmServiceImpl farmService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IOrderTrackService orderTrackService;
//
//    @Autowired
//    private ICategoryService categoryService;
////    @Test
////    public void test(){
////        orderTrackService.orderTrackAllInfo(1);
////    }
//
//    @Test
//    public void test(){
//        ResponseResult fruits = categoryService.CategoryList("推荐");
//
//    }

    @Autowired
    private IProductService productService;

    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;
    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private IOrderInfoService orderInfoService;
    @Test
    public void test(){
//        orderInfoService.sele
//        Page<ProductDetail> page = new Page<>();
//        //设置每页大小
//        page.setSize(2);
//        //设置当前页码
//        page.setCurrent(1);
//        List<ProductDetail> products = (List<ProductDetail>) productService.selectByFarmFBE(1).getData();
//        PageListUtil pageListUtil = new PageListUtil();
//        Page pages = pageListUtil.getPages(2, 2, products);
//        System.out.println(pages.getRecords());
//        PageHelper pageHelper = new PageHelper();
//        pageHelper.selectOrdersFBE(1,)
//
//        Address address = new Address();
//        address.setCurrentlocation("!23");
//        address.setDelivery("123213");
//        address.setUserId(1);
//        address.setId(9);
//        address.setDelLogic(0);
//        address.setName("123");
//        address.setPhone("1231234");
//        address.setDefaultAddress(true);
//        Integer integer = addressService.updateAddress(address);
    }
}
