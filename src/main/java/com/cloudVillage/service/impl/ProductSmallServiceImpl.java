package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Product;
import com.cloudVillage.entity.ProductSmall;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.mapper.ProductMapper;
import com.cloudVillage.mapper.ProductSmallMapper;
import com.cloudVillage.service.IProductSmallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class ProductSmallServiceImpl extends ServiceImpl<ProductSmallMapper, ProductSmall> implements IProductSmallService {

    @Autowired
    private ProductSmallMapper productSmallMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public int updateProductSmall(ProductSmall productSmall) {
        UpdateWrapper<ProductSmall> productSmallUpdateWrapper = new UpdateWrapper<>();
        productSmallUpdateWrapper.eq("id",productSmall.getId());
        int update = productSmallMapper.update(productSmall, productSmallUpdateWrapper);
        return update;
    }

    @Override
    public int deleteProductSmall(Integer id) {
        QueryWrapper<ProductSmall> productSmallQueryWrapper = new QueryWrapper<>();
        productSmallQueryWrapper.eq("id",id);
        List<ProductSmall> productSmallList = productSmallMapper.selectList(productSmallQueryWrapper);
        if(productSmallList.size() == 0){
            return -1;
        }
        int delete = productSmallMapper.delete(productSmallQueryWrapper);
        return delete;
    }

    @Override
    public int insertProductSmall(ProductSmall productSmall) {
        int insert = productSmallMapper.insert(productSmall);
        return insert;
    }

    @Override
    public ResponseResult selectProductSmall(Integer id) {
        QueryWrapper<ProductSmall> productSmallQueryWrapper = new QueryWrapper<>();
        productSmallQueryWrapper.eq("id",id);
        List<ProductSmall> productSmallList = productSmallMapper.selectList(productSmallQueryWrapper);
        if(productSmallList.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        ProductSmall productSmall = productSmallList.get(0);

        return new ResponseResult(productSmall);
    }
}
