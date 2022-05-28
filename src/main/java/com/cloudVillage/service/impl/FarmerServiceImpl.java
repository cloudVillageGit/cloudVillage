package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.Farmer;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.entity.crossResult.FarmerAll;
import com.cloudVillage.entity.crossResult.UserInfo;
import com.cloudVillage.mapper.FarmMapper;
import com.cloudVillage.mapper.FarmerMapper;
import com.cloudVillage.mapper.UserMapper;
import com.cloudVillage.mapper.UserRealInfoMapper;
import com.cloudVillage.service.IFarmerService;
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
public class FarmerServiceImpl extends ServiceImpl<FarmerMapper, Farmer> implements IFarmerService {

    @Autowired
    private FarmerMapper farmerMapper;
    @Autowired
    private FarmMapper farmMapper;
    @Autowired
    private UserRealInfoMapper userRealInfoMapper;

    @Override
    public int updateFarmer(Farmer farmer) {
        UpdateWrapper<Farmer> farmerUpdateWrapper = new UpdateWrapper<>();
        farmerUpdateWrapper.eq("id",farmer.getId());
        int update = farmerMapper.update(farmer, farmerUpdateWrapper);
        return update;
    }

    @Override
    public int deleteFarmer(Integer id) {
        QueryWrapper<Farmer> farmerQueryWrapper = new QueryWrapper<>();
        farmerQueryWrapper.eq("id",id);
        List<Farmer> farmers = farmerMapper.selectList(farmerQueryWrapper);
        if(farmers.size() == 0){
            return -1;
        }
        int delete = farmerMapper.delete(farmerQueryWrapper);
        return delete;
    }

    @Override
    public int insertFarmer(Farmer farmer) {
        int insert = farmerMapper.insert(farmer);
        return insert;
    }

    @Override
    public ResponseResult selectFarmer(Integer id) {
        QueryWrapper<Farmer> farmerQueryWrapper = new QueryWrapper<>();
        farmerQueryWrapper.eq("id",id);
        List<Farmer> farmers = farmerMapper.selectList(farmerQueryWrapper);
        if(farmers.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        return new ResponseResult(farmers.get(0));
    }

    @Override
    public ResponseResult selectFarmerAll(Farmer farmer) {

        FarmerAll farmerAll = new FarmerAll();
        farmerAll.setFarmer(farmer);
        Integer farmid = farmer.getFarmid();
        Integer userRealInfoId = farmer.getUserRealInfoId();

        QueryWrapper<Farm> farmQueryWrapper = new QueryWrapper<>();
        farmQueryWrapper.eq("id",farmid);
        List<Farm> farms = farmMapper.selectList(farmQueryWrapper);
        if(farms.size() == 0){
            farmerAll.setFarm(null);
        }else{
            farmerAll.setFarm(farms.get(0));
        }

        QueryWrapper<UserRealInfo> userRealInfoQueryWrapper = new QueryWrapper<>();
        userRealInfoQueryWrapper.eq("id",userRealInfoId);
        List<UserRealInfo> userRealInfos = userRealInfoMapper.selectList(userRealInfoQueryWrapper);
        if(farms.size() == 0){
            farmerAll.setUserInfo(null);
        }else{
            farmerAll.setUserInfo(userRealInfos.get(0));
        }
        return new ResponseResult(farmerAll);
    }
}
