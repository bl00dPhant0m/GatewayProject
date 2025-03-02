package ru.bl00dphant0m.orderservice.model.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String surname;
    private int age;
    private String email;
}
