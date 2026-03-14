package com.gab.event_driven_order_system.user.dto.getme;

import com.gab.event_driven_order_system.user.entity.statistic.Statistic;

import java.sql.Timestamp;

public record GetMeReturnDTO(String email, Integer id, String name, Integer totalOrders, Timestamp LastOrder) {
}
