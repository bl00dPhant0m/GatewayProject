package ru.bl00dphant0m.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.bl00dphant0m.orderservice.model.entity.Order;
import ru.bl00dphant0m.orderservice.model.DTO.OrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Order order);
    Order fromDto(OrderDTO orderDto);
}
