package ru.bl00dphant0m.orderservice.service.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.bl00dphant0m.orderservice.model.DTO.UserDTO;

@Service
public class UserServiceClient {
    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    @GetMapping
    public UserDTO getUser(@RequestParam long id) {
        return restTemplate.getForObject("http://localhost:8081/users/" + id, UserDTO.class);
    }
}
