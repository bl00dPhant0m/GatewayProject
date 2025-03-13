package ru.bl00dphant0m.gatewayservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class TokenValidationFilter extends AbstractGatewayFilterFactory<TokenValidationFilter.Config> {
    @Value("${Jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey;

    public TokenValidationFilter() {
        super(TokenValidationFilter.Config.class);
    }

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String URLPath = exchange.getRequest().getPath().toString();

            // Пропускаем запросы на регистрацию без проверки токена
            if (URLPath.startsWith("/security")) {
                log.info("Skipping token validation for registration path: {}", URLPath);
                return chain.filter(exchange);
            }

            // Извлекаем токен из заголовка
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                log.warn("Token is missing or invalid for path: {}", URLPath);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                // Проверяем токен
                Claims claims = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token.substring(7)) // Убираем "Bearer " из токена
                        .getPayload();

                log.info("Token validated successfully for path: {}", URLPath);
            } catch (JwtException | IllegalArgumentException e) {
                log.error("Token validation failed for path: {}: {}", URLPath, e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Передаем запрос дальше по цепочке
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}