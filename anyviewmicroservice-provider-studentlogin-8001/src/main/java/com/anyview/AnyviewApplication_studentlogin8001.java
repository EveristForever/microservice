package com.anyview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
//扫描mapper接口
@MapperScan("com.anyview.dao")
@EnableCaching
@EnableEurekaClient
public class AnyviewApplication_studentlogin8001 {

    public static void main(String[] args) {
        SpringApplication.run(AnyviewApplication_studentlogin8001.class, args);
    }

}

