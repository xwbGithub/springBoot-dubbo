package com.atguigu.gmail.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.OrderService;
import com.atguigu.gmail.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements OrderService {
    //@Reference注解的作用是在zookeeper中找服务提供者
    //@Reference(check = false)启动时是否检查该服务在zookeeper中存在,默认是true。
    //@Reference(timeout = 3000)
    //@Reference(stub = "com.atguigu.gmail.service.impl.UserServiceStub")本地存根
    //@Reference(url = "127.0.0.1:20881") //dubbo直连
    @Reference(loadbalance = "roundrobin")//负载均衡机制
    private UserService userService;

    //@Reference(timeout = 2000)
    @Override
    @HystrixCommand(fallbackMethod = "exceptionMethod")
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id" + userId);
        return userService.getUserAddressList(userId);
    }

    /**
     * 容错方法
     *
     * @return
     */
    public List<UserAddress> exceptionMethod() {
        List<UserAddress> arrayList = new ArrayList<>();
        UserAddress userAddress = new UserAddress();
        userAddress.setId(10);
        userAddress.setPhoneNum("123456");
        userAddress.setUserAddress("上海市");
        arrayList.add(userAddress);
        userAddress.setConsignee("测试");
        userAddress.setIsDefault("Y");
        return arrayList;
    }
}
