package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 农产品分类表 服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface ICategoryService extends IService<Category> {
    public ResponseResult CategoryList(String keyWord);
    public ResponseResult searchTop(String categoryTop);
    public ResponseResult searchSecond(String categorySecond);
}
