package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.skubatko.dev.topjava.api.api.AdminUserApi;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.service.UserService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminUserController extends AbstractUserController implements AdminUserApi {

    private final UserService service;

    static final String REST_URL = "/api/admin/v1/users";

    @Override
    public ResponseEntity<UserTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<UserTO>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<UserTO> getByEmail(String email) {
        log.info("getByEmail {}", email);
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @Override
    public ResponseEntity<Void> enable(Integer id, Boolean enabled) {
        log.info(Boolean.TRUE.equals(enabled) ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserTO> create(UserCreateTO newUser) {
        log.info("create {}", newUser);
        UserTO created = service.create(newUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, UserTO user) {
        log.info("update {} with id={}", user, id);
        service.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
