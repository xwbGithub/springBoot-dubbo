package com.atguigu.gmail.config;

import com.alibaba.dubbo.config.*;
import com.atguigu.gmail.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyDubboConfig {
    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("order-service-consumer");
        return applicationConfig;
    }
    /**
     * 客户端config配置
     *
     * @return consumerConfig
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(30000);
        consumerConfig.setCheck(false);
        consumerConfig.setRetries(3);
        consumerConfig.setVersion("*");
        return consumerConfig;
    }

    /**
     * 注册中心配置
     *
     * @return registryConfig
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setPort(2181);
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setCheck(true);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    /**
     * 消费者端配置
     *
     * @return referenceConfig
     */
    @Bean
    public ReferenceConfig<UserService> referenceConfig() {
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setVersion("*");
        referenceConfig.setTimeout(10000);

        //配置每一个method的属性
        MethodConfig config = new MethodConfig();
        config.setName("getUserAddressList");
        config.setTimeout(20000);
        config.setRetries(3);

        //将method的设置关联到service配置中
        List<MethodConfig> methodConfig = new ArrayList<>();
        methodConfig.add(config);
        referenceConfig.setMethods(methodConfig);
        return referenceConfig;
    }
}
