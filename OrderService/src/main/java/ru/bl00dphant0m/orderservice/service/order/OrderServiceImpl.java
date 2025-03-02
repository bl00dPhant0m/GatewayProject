package ru.bl00dphant0m.orderservice.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.bl00dphant0m.orderservice.mapper.OrderMapper;
import ru.bl00dphant0m.orderservice.model.DTO.OrderDTO;
import ru.bl00dphant0m.orderservice.model.DTO.UserDTO;
import ru.bl00dphant0m.orderservice.model.entity.Order;
import ru.bl00dphant0m.orderservice.repository.OrderRepository;
import ru.bl00dphant0m.orderservice.service.client.UserServiceClient;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO getOrderWithUser(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        UserDTO user = userServiceClient.getUser(order.getUserId());

        OrderDTO orderDTO = orderMapper.toDto(order);
        orderDTO.setUser(user);

        return orderDTO;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }
}
