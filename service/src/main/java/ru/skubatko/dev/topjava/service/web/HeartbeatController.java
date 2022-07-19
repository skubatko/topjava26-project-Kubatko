package ru.skubatko.dev.topjava.service.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.heartbeat.api.HeartbeatApi;

@RestController
public class HeartbeatController implements HeartbeatApi {

    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong Heartbeat Api");
    }
}
