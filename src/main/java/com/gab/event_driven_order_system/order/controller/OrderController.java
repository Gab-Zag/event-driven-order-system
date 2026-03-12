package com.gab.event_driven_order_system.order.controller;

import com.gab.event_driven_order_system.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }
}
