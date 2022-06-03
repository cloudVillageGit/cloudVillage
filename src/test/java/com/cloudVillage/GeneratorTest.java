package com.cloudVillage;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudVillage.Util.PageListUtil;
import com.cloudVillage.config.QiNiuYunConfig;
import com.cloudVillage.controller.PageHelper;
import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.service.ICategoryService;
import com.cloudVillage.service.IOrderInfoService;
import com.cloudVillage.service.IProductService;
import com.cloudVillage.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GeneratorTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void test(){
        categoryService.searchTop(null);
    }
}
