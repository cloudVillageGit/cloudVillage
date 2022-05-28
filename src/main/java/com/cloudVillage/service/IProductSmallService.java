package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.ProductSmall;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudVillage.entity.UserRealInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IProductSmallService extends IService<ProductSmall> {
    int updateProductSmall(ProductSmall productSmall);
    int deleteProductSmall(Integer id);
    int insertProductSmall(ProductSmall productSmall);
    ResponseResult selectProductSmall(Integer id);
}
