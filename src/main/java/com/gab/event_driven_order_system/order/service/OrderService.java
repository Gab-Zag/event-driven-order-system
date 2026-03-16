package com.gab.event_driven_order_system.order.service;

import com.gab.event_driven_order_system.order.dto.create.CreateOrderDTO;
import com.gab.event_driven_order_system.order.dto.myorder.MyOrderDTO;
import com.gab.event_driven_order_system.order.entity.Order;
import com.gab.event_driven_order_system.order.entity.Status;
import com.gab.event_driven_order_system.order.repository.OrderRepository;
import com.gab.event_driven_order_system.user.entity.user.User;
import com.gab.event_driven_order_system.user.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    OrderService(OrderRepository orderRepository, UserService userService){
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public String requestOrder(CreateOrderDTO data){

        User user = userService.findUser(data.userId());

        Order order = new Order(user, data.product(), data.price(), Status.PENDING, Timestamp.valueOf(LocalDateTime.now()));

        orderRepository.save(order);
        return "Pedido realizado com sucesso";
    }

    public List<Order> myOrders(MyOrderDTO data){
        return orderRepository.findByUserId(userService.findUser(data.id()));
    }
}
