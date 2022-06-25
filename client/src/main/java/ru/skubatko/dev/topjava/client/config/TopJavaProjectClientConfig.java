package ru.skubatko.dev.topjava.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.skubatko.dev.topjava.client.TopJavaProjectClient;
import ru.skubatko.dev.topjava.common.SerializationHelper;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;

@Configuration
@EnableConfigurationProperties(TopJavaProjectClientProps.class)
public class TopJavaProjectClientConfig {

    @Bean
    @ConditionalOnMissingBean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(jsonObjectMapper());
        jsonConverter.setDefaultCharset(null);
        return jsonConverter;
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper jsonObjectMapper() {
        return SerializationHelper.preconfiguredJsonObjectMapper();
    }

    @Bean
    @ConditionalOnMissingBean
    public TopJavaProjectClient topJavaProjectClient(TopJavaProjectClientProps props) {
        val webClient =
                WebClient.builder()
                        .baseUrl(props.getUrl())
                        .clientConnector(
                                new ReactorClientHttpConnector(
                                        HttpClient
                                                .create()
                                                .option(CONNECT_TIMEOUT_MILLIS, (int) props.getConnectionTimeout().toMillis())
                                                .doOnConnected(connection -> connection.addHandlerLast(
                                                        new ReadTimeoutHandler((int) props.getReadTimeout().getSeconds())))
                                )).build();
        return new TopJavaProjectClient(webClient);
    }
}
