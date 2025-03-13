package ru.bl00dphant0m.securityservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
//*
@Component
@ConfigurationProperties(prefix = "custom.config")
public class CustomConfig {
    private String dbUrl;
}
