package ru.bl00dphant0m.configservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

//
@RequestMapping("/config")
public class ConfigController {
    @Value("${config.security-service.db-url}")
    private String securityServiceDBUrl;

    @GetMapping("/{serviceName}")
    public ResponseEntity<Map<String, String>> getConfig(@PathVariable String serviceName) {
        Map<String, String> config = new HashMap<>();
        if (serviceName.equals("security-service")){
            config.put("db-url", securityServiceDBUrl);
        }
        //вернуть мэп где кей - проперти, вэлью - значение. Возвращать разные мап для сервисов
    }
}
