package com.gab.event_driven_order_system.order.dto.create;

import java.math.BigDecimal;

public record CreateOrderDTO(Integer userId, String product, BigDecimal price) {
}
