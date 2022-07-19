package ru.skubatko.dev.topjava.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import ru.skubatko.dev.topjava.api.heartbeat.api.HeartbeatApi;

@RequiredArgsConstructor
public class TopJavaProjectClient implements HeartbeatApi {

    private final WebClient webClient;

    private final String baseUrl = "/heartbeat/v1";

    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok(
                webClient
                        .get()
                        .uri(baseUrl + "/ping")
                        .retrieve()
                        .bodyToMono(String.class)
                        .blockOptional()
                        .orElse("")
        );
    }
}
