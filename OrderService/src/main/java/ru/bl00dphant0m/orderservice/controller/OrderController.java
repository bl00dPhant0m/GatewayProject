package ru.bl00dphant0m.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bl00dphant0m.orderservice.model.DTO.OrderDTO;
import ru.bl00dphant0m.orderservice.model.entity.Order;
import ru.bl00dphant0m.orderservice.service.order.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderWithUser(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @DeleteMapping
    public ResponseEntity<Order> deleteOrder(@RequestParam long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
