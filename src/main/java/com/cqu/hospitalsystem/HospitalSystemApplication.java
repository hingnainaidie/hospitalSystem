package com.cqu.hospitalsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.cqu.hospitalsystem.dao")
@EnableCaching      //开启缓存功能
public class HospitalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSystemApplication.class, args);
    }

}
