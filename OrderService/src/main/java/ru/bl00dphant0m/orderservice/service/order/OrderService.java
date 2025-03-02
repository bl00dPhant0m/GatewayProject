package ru.bl00dphant0m.orderservice.service.order;

import ru.bl00dphant0m.orderservice.model.DTO.OrderDTO;
import ru.bl00dphant0m.orderservice.model.entity.Order;

public interface OrderService {
    OrderDTO getOrderWithUser(long orderId);
    Order createOrder(Order order);
    void deleteOrder(long orderId);
}
