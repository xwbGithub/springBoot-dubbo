package com.atguigu.gmail.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.OrderService;
import com.atguigu.gmail.service.UserService;

@Service
public class UserServiceImpl implements OrderService {

    @Reference //在zookeeper中找服务提供者
    private UserService userService;

    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id" + userId);
        return userService.getUserAddressList(userId);
    }
}
