package com.cloudVillage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cloudVillage.mapper")
@SpringBootApplication
public class cloudVillageApplication {
    public static void main(String[] args) {
        SpringApplication.run(cloudVillageApplication.class);
    }
}

