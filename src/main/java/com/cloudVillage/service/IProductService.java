package com.cloudVillage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.entity.crossResult.ProductDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IProductService extends IService<Product> {
    public ResponseResult searchProduct(String keyWord);
    public ResponseResult productDetail(Integer id);
    int updateProduct(Product product);
    int deleteProduct(Integer id);
    ResponseResult insertProduct(Product product);

    public ResponseResult selectByFarmFBE(Integer farmId,String keyWord);

//    void selectByFarmFBE(Page<ProductDetail> page);
}
