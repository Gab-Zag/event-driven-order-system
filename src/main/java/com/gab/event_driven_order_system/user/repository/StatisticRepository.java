package com.gab.event_driven_order_system.user.repository;

import com.gab.event_driven_order_system.user.entity.statistic.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic,Integer> {
}
