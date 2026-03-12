package com.gab.event_driven_order_system.order.service;

import com.gab.event_driven_order_system.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
