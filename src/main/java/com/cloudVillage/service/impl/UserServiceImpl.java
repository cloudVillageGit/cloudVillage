package com.cloudVillage.service.impl;

import com.cloudVillage.entity.User;
import com.cloudVillage.mapper.UserMapper;
import com.cloudVillage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
