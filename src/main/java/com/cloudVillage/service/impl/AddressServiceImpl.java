package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.mapper.AddressMapper;
import com.cloudVillage.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Update;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Integer insertAddress(Address address) {

        if( address.getDefaultAddress()){
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("defaultAddress",0);
            updateWrapper.eq("userId",address.getUserId());
            addressMapper.update(null,updateWrapper);
        }

        int insert = addressMapper.insert(address);

        return insert;
    }

    @Override
    public Integer updateAddress(Address address) {

        if( address.getDefaultAddress()){
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("defaultAddress",0);
            updateWrapper.eq("userId",address.getUserId());
            addressMapper.update(null,updateWrapper);
        }

        UpdateWrapper<Address> addressUpdateWrapper = new UpdateWrapper<>();
        addressUpdateWrapper.eq("id",address.getId());
        System.out.println(address.toString());
        int update = addressMapper.update(address, addressUpdateWrapper);
        return update;
    }

    @Override
    public Integer deleteAddress(Integer id) {
        int delete = addressMapper.deleteById(id);
        return delete;
    }

    @Override
    public ResponseResult selectAddress(Integer id) {

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("id",id);
        List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
        if(addressList.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        return new ResponseResult(addressList.get(0));
    }

    @Override
    public ResponseResult selectAddressByUserId(Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("userId",userId);
        List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
        if(addressList.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        return new ResponseResult(addressList);
    }
}
