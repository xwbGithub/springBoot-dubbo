package com.atguigu.gmail;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、导入依赖
 *    1.1、导入dubbo-starter
 *    1.2、导入dubbo的其他依赖
 */
@SpringBootApplication
@EnableDubbo /*开启基于注解的dubbo功能*/
//@DubboComponentScan(basePackages = "com.atguigu.gmail")
public class BootUserServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootUserServiceProviderApplication.class, args);
    }
}

