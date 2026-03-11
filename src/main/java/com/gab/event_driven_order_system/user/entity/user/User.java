package com.gab.event_driven_order_system.user.entity.user;

import com.gab.event_driven_order_system.user.entity.statistic.Statistic;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(mappedBy = "userId", fetch = FetchType.LAZY)
    private Statistic statistic;
}
