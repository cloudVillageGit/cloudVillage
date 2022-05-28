package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.mapper.UserRealInfoMapper;
import com.cloudVillage.service.IUserRealInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户真实信息 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class UserRealInfoServiceImpl extends ServiceImpl<UserRealInfoMapper, UserRealInfo> implements IUserRealInfoService {

    @Autowired
    private UserRealInfoMapper userRealInfoMapper;
    @Override
    public int updateUserInfo(UserRealInfo userInfo) {
        UpdateWrapper<UserRealInfo> userRealInfoUpdateWrapper = new UpdateWrapper<>();
        userRealInfoUpdateWrapper.eq("id",userInfo.getId());
        int update = userRealInfoMapper.update(userInfo, userRealInfoUpdateWrapper);
        return update;
    }

    @Override
    public int deleteUserInfo(Integer id) {
        QueryWrapper<UserRealInfo> userRealInfoQueryWrapper = new QueryWrapper<>();
        userRealInfoQueryWrapper.eq("id",id);
        List<UserRealInfo> userRealInfos = userRealInfoMapper.selectList(userRealInfoQueryWrapper);
        if(userRealInfos.size() == 0){
            return -1;
        }
        int delete = userRealInfoMapper.delete(userRealInfoQueryWrapper);
        return delete;
    }

    @Override
    public int insertUserInfo(UserRealInfo userInfo) {
        int insert = userRealInfoMapper.insert(userInfo);
        return insert;
    }

    @Override
    public ResponseResult selectUserRealInfo(Integer id) {
        QueryWrapper<UserRealInfo> userRealInfoQueryWrapper = new QueryWrapper<>();
        userRealInfoQueryWrapper.eq("id",id);
        List<UserRealInfo> userRealInfos = userRealInfoMapper.selectList(userRealInfoQueryWrapper);
        if(userRealInfos.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        return new ResponseResult(userRealInfos.get(0));
    }
}
