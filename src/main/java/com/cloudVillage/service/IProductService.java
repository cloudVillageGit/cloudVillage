package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Product;
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
public interface IProductService extends IService<Product> {
    // 搜索
    public ResponseResult searchProduct(String keyWord);

    // 进入购买界面需要拥有的详细数据界面
    public ResponseResult productDetail(Integer id);

    int updateProduct(Product product);
    int deleteProduct(Integer id);
    int insertProduct(Product product);
}
