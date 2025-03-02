package ru.bl00dphant0m.orderservice.model.DTO;

import lombok.Data;

@Data
public class OrderDTO {
    private String name;
    private int quantity;
    private UserDTO user;
}
