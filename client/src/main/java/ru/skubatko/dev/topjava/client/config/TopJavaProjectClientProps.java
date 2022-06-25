package ru.skubatko.dev.topjava.client.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("msa.integration.topjava-project")
public class TopJavaProjectClientProps {
    private final String url;
    private final Duration readTimeout;
    private final Duration connectionTimeout;
}
