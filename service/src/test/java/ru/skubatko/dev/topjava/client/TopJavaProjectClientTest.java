package ru.skubatko.dev.topjava.client;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skubatko.dev.topjava.service.TopJavaProjectApplication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Клиент TopJava Project")
@SpringBootTest(classes = TopJavaProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TopJavaProjectClientTest {

    @Autowired
    private TopJavaProjectClient client;

    @DisplayName("должен отдавать heartbeat")
    @SneakyThrows
    @Test
    void shouldReturnHeartbeat() {
        val result = client.ping();

        assertThat(result.getBody())
                .isNotNull()
                .isEqualTo("Pong Heartbeat Api");
    }
}
