package com.cloudVillage.service.impl;

import com.cloudVillage.entity.Farm;
import com.cloudVillage.mapper.FarmMapper;
import com.cloudVillage.service.IFarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class FarmServiceImpl extends ServiceImpl<FarmMapper, Farm> implements IFarmService {

    @Autowired
    private FarmMapper farmMapper;

    @Override
    public void selectAll() {
        Farm farm = farmMapper.selectById(1);
        System.out.println(farm.toString());
    }
}
