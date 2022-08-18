package ru.skubatko.dev.topjava.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skubatko.dev.topjava.common.SerializationHelper;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return SerializationHelper.preconfiguredJsonObjectMapper();
    }
}
