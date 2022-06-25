package ru.skubatko.dev.topjava.service.web;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("Контроллер Heartbeat")
@WebFluxTest
class HeartbeatControllerTest {

    @Autowired
    private WebTestClient webClient;

    private final String baseUrl = "/heartbeat/v1";

    @DisplayName("должен отвечать на пинг")
    @SneakyThrows
    @Test
    void shouldReplayOnPing() {
        // given
        val url = baseUrl + "/ping";

        // when + then
        webClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Pong Heartbeat Api");
    }
}
