package com.cloudVillage.service.impl;

import com.cloudVillage.entity.Farmer;
import com.cloudVillage.mapper.FarmerMapper;
import com.cloudVillage.service.IFarmerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FarmerServiceImpl extends ServiceImpl<FarmerMapper, Farmer> implements IFarmerService {

}
