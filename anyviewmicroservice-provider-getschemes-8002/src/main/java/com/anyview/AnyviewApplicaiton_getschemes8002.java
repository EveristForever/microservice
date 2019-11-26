package com.anyview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@MapperScan("com.anyview.dao")
@EnableEurekaClient
@EnableCaching
public class AnyviewApplicaiton_getschemes8002 {
    public static void main(String[] args) {
        SpringApplication.run(AnyviewApplicaiton_getschemes8002.class,args);
    }
}
