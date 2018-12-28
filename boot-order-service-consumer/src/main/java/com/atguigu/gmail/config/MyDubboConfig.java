package com.atguigu.gmail.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDubboConfig {
    /**
     * 客户端config配置
     *
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        consumerConfig.setCheck(false);
        consumerConfig.setRetries(3);
        consumerConfig.setVersion("*");
        return consumerConfig;
    }

    /**
     * 注册中心配置
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setPort(2181);
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setCheck(false);
        return registryConfig;
    }
}
