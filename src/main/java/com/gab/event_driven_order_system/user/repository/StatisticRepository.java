package com.gab.event_driven_order_system.user.repository;

import com.gab.event_driven_order_system.user.entity.statistic.Statistic;
import com.gab.event_driven_order_system.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic,Integer> {
    Optional<Statistic> findByUserId(User userId);
}
