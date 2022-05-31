package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudVillage.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IAddressService extends IService<Address> {
    public Integer insertAddress(Address address);
    public Integer updateAddress(Address address);
    public Integer deleteAddress(Integer id);
    public ResponseResult selectAddress(Integer id);
    public ResponseResult selectAddressByUserId(Integer userId);
}
