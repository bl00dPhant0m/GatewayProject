package ru.bl00dphant0m.securityservice.model;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
