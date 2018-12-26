package com.atguigu.gmail.service.impl;

import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.UserService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 本地存根：<br/>
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，<br>
 * 比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给 Stub [1]，<br>
 * 然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
 */
public class UserServiceStub implements UserService {
    private final UserService userService;

    /**
     * 传入的是userService的代理对象
     *
     * @param userService
     */

    public UserServiceStub(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserService ......");
        if (!StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
