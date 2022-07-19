package ru.skubatko.dev.topjava.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic")
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "REST API documentation",
                version = "1.0",
                description = "Приложение по <a href='https://javaops.ru/view/topjava'>курсу TopJava</a> (выпускной проект)",
                contact = @io.swagger.v3.oas.annotations.info.Contact(url = "https://skubatko.ru/#contacts", name = "Sergey Kubatko", email = "admin@skubatko.ru")
        ),
        security = @SecurityRequirement(name = "basicAuth"))
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi openApi(@Value("${spring.application.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("REST API")
//                .pathsToMatch("/api/**")
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().version(appVersion)))
                .build();
    }
}
