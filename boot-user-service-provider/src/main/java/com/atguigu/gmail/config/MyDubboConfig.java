package com.atguigu.gmail.config;

import com.alibaba.dubbo.config.*;
import com.atguigu.gmail.service.UserService;
import com.atguigu.gmail.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * dubbo配置文件
 */
@Configuration
public class MyDubboConfig {
    /**
     * 相当于此bean替代了dubbo.application.* 标签
     *
     * @return applicationConfig
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-service-provider");//设置应用名
        return applicationConfig;
    }

    /**
     * 设置注册中心配置
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);
        registryConfig.setProtocol("zookeeper");
        registryConfig.setCheck(false);
        return registryConfig;
    }

    /**
     * 通信协议配置项
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20880);
        protocolConfig.setName("dubbo");
        return protocolConfig;
    }

    /**
     * 监控中心
     *
     * @return
     */
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        return monitorConfig;
    }

    /**
     * 服务提供接口
     *
     * @param userService 入参的userService<br>
     *                    是从ioc中拿去 @Component public class UserServiceImpl implements UserService {...}
     * @return
     */
    @Bean
    public ServiceConfig<UserService> userServiceServiceConfig(UserServiceImpl userService) {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setTimeout(3000);
        serviceConfig.setRetries(3);

        //配置每一个method的属性
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getUserAddressList");
        methodConfig.setTimeout(3000);
        methodConfig.setRetries(2);

        //将method的设置关联到service的配置中
        List<MethodConfig> methods = new ArrayList();
        methods.add(methodConfig);
        serviceConfig.setMethods(methods);

        return serviceConfig;
    }

    /**
     * providerConfig的统一配置
     *
     * @return
     */
    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(3000);
        providerConfig.setRetries(3);
        return providerConfig;
    }
}
