package ru.skubatko.dev.topjava.service.web;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер Heartbeat")
class HeartbeatControllerTest extends AbstractControllerTest {

    private final String baseUrl = "/heartbeat/v1";

    @DisplayName("должен отвечать на пинг")
    @Test
    @SneakyThrows
    void shouldReplayOnPing() {
        val url = baseUrl + "/ping";
        perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Pong Heartbeat Api"));
    }
}
