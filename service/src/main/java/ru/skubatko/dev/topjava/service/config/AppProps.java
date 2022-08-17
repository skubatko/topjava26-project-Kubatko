package ru.skubatko.dev.topjava.service.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.LocalTime;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "app")
public class AppProps {
    LocalTime voteDeadlineTime;
}
