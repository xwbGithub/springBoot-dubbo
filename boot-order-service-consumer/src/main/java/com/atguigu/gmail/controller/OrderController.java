package com.atguigu.gmail.controller;

import com.atguigu.gmail.bean.UserAddress;
import com.atguigu.gmail.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/initOrder")
    public List<UserAddress> initOrder(@RequestParam("userId") String userId) {
        return orderService.initOrder(userId);
    }
}
