package com.cloudVillage.service.impl;

import com.cloudVillage.entity.Address;
import com.cloudVillage.mapper.AddressMapper;
import com.cloudVillage.service.IAddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
