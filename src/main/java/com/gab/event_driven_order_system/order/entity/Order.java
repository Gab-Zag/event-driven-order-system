package com.gab.event_driven_order_system.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gab.event_driven_order_system.user.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userId;

    private String product;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
