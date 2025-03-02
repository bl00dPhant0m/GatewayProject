package ru.bl00dphant0m.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bl00dphant0m.orderservice.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
