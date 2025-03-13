package ru.bl00dphant0m.securityservice.client;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.bl00dphant0m.securityservice.configuration.CustomConfig;

@Component
@AllArgsConstructor
public class ConfigLoader implements CommandLineRunner {
    private final CustomConfig customConfig;

    @Override
    public void run(String... args) throws Exception {
        //*
       // customConfig.setUrl();
        // через рест темплейт делать запрос на конфиг сервис для получения информации, принимать меп. В остальных сервисах тоже самое
    }
}
