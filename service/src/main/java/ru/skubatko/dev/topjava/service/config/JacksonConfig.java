package ru.skubatko.dev.topjava.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import ru.skubatko.dev.topjava.common.SerializationHelper;

@Configuration
public class JacksonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(jsonObjectMapper());
        jsonConverter.setDefaultCharset(null);
        return jsonConverter;
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return SerializationHelper.preconfiguredJsonObjectMapper();
    }
}
