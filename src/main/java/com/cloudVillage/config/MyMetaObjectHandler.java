package com.cloudVillage.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // OrderTrackMedium
        this.setFieldValByName("worktime", LocalDateTime.now(), metaObject);
        // Discover
        this.setFieldValByName("posttime", LocalDateTime.now(), metaObject);
        // Evaluate
        this.setFieldValByName("evaluatetime", LocalDateTime.now(), metaObject);
        // OrderInfo User
        this.setFieldValByName("createtime", LocalDateTime.now(), metaObject);


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // OrderTrackMedium
        this.setFieldValByName("worktime", LocalDateTime.now(), metaObject);
        // Discover
        this.setFieldValByName("posttime", LocalDateTime.now(), metaObject);
        // Evaluate
        this.setFieldValByName("evaluatetime", LocalDateTime.now(), metaObject);
        // OrderInfo User
        this.setFieldValByName("createtime", LocalDateTime.now(), metaObject);

    }
}

