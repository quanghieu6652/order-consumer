package com.example.order_consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.order_consumer.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  // các method tuỳ chọn
}
