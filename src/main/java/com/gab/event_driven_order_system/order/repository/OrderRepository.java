package com.gab.event_driven_order_system.order.repository;

import com.gab.event_driven_order_system.order.entity.Order;
import com.gab.event_driven_order_system.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(User userId);
}
