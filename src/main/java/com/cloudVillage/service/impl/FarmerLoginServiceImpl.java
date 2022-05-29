package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.FarmerLogin;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.mapper.FarmerLoginMapper;
import com.cloudVillage.mapper.FarmerMapper;
import com.cloudVillage.service.IFarmerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/14:58
 * @Description:
 */
@Service
public class FarmerLoginServiceImpl extends ServiceImpl<FarmerLoginMapper, FarmerLogin> implements IFarmerLoginService {


    @Autowired
    private FarmerLoginMapper farmerLoginMapper;

    @Override
    public int updateFarmerLogin(FarmerLogin farmerLogin) {
        UpdateWrapper<FarmerLogin> farmerLoginUpdateWrapper = new UpdateWrapper<>();
        farmerLoginUpdateWrapper.eq("id",farmerLogin.getId());
        int update = farmerLoginMapper.update(farmerLogin, farmerLoginUpdateWrapper);
        return update;
    }

    @Override
    public int deleteFarmerLogin(Integer id) {
        QueryWrapper<FarmerLogin> farmerLoginQueryWrapper = new QueryWrapper<>();
        farmerLoginQueryWrapper.eq("id",id);
        List<FarmerLogin> farmerLogins = farmerLoginMapper.selectList(farmerLoginQueryWrapper);
        if(farmerLogins.size() == 0){
            return -1;
        }
        int delete = farmerLoginMapper.delete(farmerLoginQueryWrapper);
        return delete;
    }

    @Override
    public int insertFarmerLogin(FarmerLogin farmerLogin) {
        int insert = farmerLoginMapper.insert(farmerLogin);
        return insert;
    }


    @Override
    public ResponseResult selectFarmerLogin(String name,String password) {
        QueryWrapper<FarmerLogin> farmerLoginQueryWrapper = new QueryWrapper<>();
        farmerLoginQueryWrapper.eq("fName",name);
        List<FarmerLogin> farmerLogins = farmerLoginMapper.selectList(farmerLoginQueryWrapper);
        if(farmerLogins.size() == 0){
            return new ResponseResult(606,"无该用户");
        }else{
            FarmerLogin farmerLogin = farmerLogins.get(0);
            String fpassword = farmerLogin.getFpassword();
            Integer farmId = farmerLogin.getFarmid();
            if( fpassword.equals(password) ){
                return new ResponseResult(farmId);
            }else{
                return new ResponseResult(607,"用户密码错误");
            }
        }
    }
}
