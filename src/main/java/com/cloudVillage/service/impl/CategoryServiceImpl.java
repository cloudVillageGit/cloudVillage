package com.cloudVillage.service.impl;

import com.cloudVillage.entity.Category;
import com.cloudVillage.mapper.CategoryMapper;
import com.cloudVillage.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 农产品分类表 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
