package ru.skubatko.dev.topjava.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${spring.application.version}") String appVersion) {
        val openAPI = new OpenAPI();
        openAPI.info(
                new Info()
                        .title("api")
                        .description("TopJava personal project API")
                        .contact(new Contact().name("Sergey Kubatko"))
                        .version(appVersion)
        );
        return openAPI;
    }
}
