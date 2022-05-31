package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.entity.crossResult.FarmAddress;
import com.cloudVillage.mapper.FarmMapper;
import com.cloudVillage.service.IAddressService;
import com.cloudVillage.service.IFarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FarmServiceImpl extends ServiceImpl<FarmMapper, Farm> implements IFarmService {

    @Autowired
    private FarmMapper farmMapper;

    @Autowired
    private IAddressService addressService;

    @Override
    public int updateFarm(Farm farm) {
        UpdateWrapper<Farm> farmUpdateWrapper = new UpdateWrapper<>();
        farmUpdateWrapper.eq("id",farm.getId());
        int update = farmMapper.update(farm, farmUpdateWrapper);
        return update;
    }

    @Override
    public int deleteFarm(Integer id) {
        QueryWrapper<Farm> farmQueryWrapper = new QueryWrapper<>();
        farmQueryWrapper.eq("id",id);
        List<Farm> farms = farmMapper.selectList(farmQueryWrapper);
        if(farms.size() == 0){
            return -1;
        }
        int delete = farmMapper.delete(farmQueryWrapper);
        return delete;
    }

    @Override
    public int insertFarm(Farm farm) {
        int insert = farmMapper.insert(farm);
        return insert;
    }

    @Override
    public ResponseResult selectFarm(Integer id) {
        QueryWrapper<Farm> farmQueryWrapper = new QueryWrapper<>();
        farmQueryWrapper.eq("id",id);
        List<Farm> farms = farmMapper.selectList(farmQueryWrapper);
        if(farms.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        return new ResponseResult(farms.get(0));
    }


    public ResponseResult selectFarmDetail(Integer id){
        FarmAddress farmAddress = new FarmAddress();
        ResponseResult selectFarm = this.selectFarm(id);
            Farm farm = (Farm) selectFarm.getData();
            farmAddress.setFarm(farm);
            Integer addressid = farm.getAddressid();
            ResponseResult address = addressService.selectAddress(addressid);
            Address addressData = (Address) address.getData();
            farmAddress.setAddress(addressData);
        return new ResponseResult(farmAddress);
    }
}
