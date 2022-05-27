package com.cloudVillage.mapper;

import com.cloudVillage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface UserMapper extends BaseMapper<User> {
}
