package com.atguigu.gmail.service.impl;

import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0") //暴露服务
@Component
public class UserServiceImpl1 implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserServiceImpl..old.....1.0.0");
        UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		//try {
        //	Thread.sleep(6000);
        //} catch (InterruptedException e) {
        //	e.printStackTrace();
        //}
        //if (Math.random() > 0.5) {
        //    throw new RuntimeException();
        //}
        return Arrays.asList(address1, address2);
    }

}
