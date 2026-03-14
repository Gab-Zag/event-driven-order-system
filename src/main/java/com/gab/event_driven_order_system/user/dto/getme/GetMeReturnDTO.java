package com.gab.event_driven_order_system.user.dto.getme;

import java.sql.Timestamp;

public record GetMeReturnDTO(Integer id, String name,String email, Integer totalOrder, Timestamp lastOrder) {
}
