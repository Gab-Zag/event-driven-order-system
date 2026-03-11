package com.gab.event_driven_order_system.user.entity.statistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gab.event_driven_order_system.user.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userId;

    @Column(name = "total_orders")
    private Integer totalOrders;

    @Column(name = "lastorder_date")
    private Timestamp LastOrder;
}
