package com.gab.event_driven_order_system.order.controller;

import com.gab.event_driven_order_system.order.dto.create.CreateOrderDTO;
import com.gab.event_driven_order_system.order.dto.myorder.MyOrderDTO;
import com.gab.event_driven_order_system.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<String> requestOrder(@RequestBody CreateOrderDTO data){
        return ResponseEntity.ok(orderService.requestOrder(data));
    }

    @GetMapping("/my")
    public ResponseEntity myOrders(@RequestBody MyOrderDTO data){
        orderService.myOrders(data);
        return ResponseEntity.ok().build();
    }
}
