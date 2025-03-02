package ru.bl00dphant0m.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;

@Configuration
public class GatewayConfig {

//    @Bean
//    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", r -> r.path("/users/**").uri("http://localhost:8081"))
//                .route("order-service", r -> r.path("/orders/**").uri("http://localhost:8082"))
//                .build();
//    }
}
