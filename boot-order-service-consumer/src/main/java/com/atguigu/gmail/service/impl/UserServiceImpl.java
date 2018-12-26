package com.atguigu.gmail.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.OrderService;
import com.atguigu.gmail.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements OrderService {
    //@Reference注解的作用是在zookeeper中找服务提供者
    //@Reference(check = false)启动时是否检查该服务在zookeeper中存在,默认是true。
    //@Reference(timeout = 3000)
    //@Reference(stub = "com.atguigu.gmail.service.impl.UserServiceStub")本地存根
    @Reference
    private UserService userService;

    //@Reference(timeout = 2000)
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id" + userId);
        return userService.getUserAddressList(userId);
    }
}
