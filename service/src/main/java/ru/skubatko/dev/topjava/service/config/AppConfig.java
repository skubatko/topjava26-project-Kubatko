package ru.skubatko.dev.topjava.service.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ConfigurationPropertiesScan(basePackageClasses = AppConfig.class)
public class AppConfig {
}
