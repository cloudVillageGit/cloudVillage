package com.cloudVillage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.entity.User;
import com.cloudVillage.mapper.UserMapper;
import com.cloudVillage.service.IUserService;
import com.cloudVillage.service.impl.FarmServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GeneratorTest {
    @Autowired
    private FarmServiceImpl farmService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Test
    public void test(){
        farmService.selectAll();
    }


}
