package ru.skubatko.dev.topjava.service.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.skubatko.dev.topjava.api.api.UserProfileApi;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.service.UserService;
import ru.skubatko.dev.topjava.service.web.admin.AbstractUserController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserProfileController extends AbstractUserController implements UserProfileApi {

    private final UserService userService;

    static final String REST_URL = "/api/user/v1/profiles";

    @Override
    public ResponseEntity<UserTO> get() {
        return ResponseEntity.ok(userService.getAuth());
    }

    @Override
    public ResponseEntity<Void> delete() {
        userService.deleteAuth();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserTO> register(UserCreateTO user) {
        log.info("register {}", user);
        UserTO created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    public ResponseEntity<Void> update(UserTO user) {
        log.info("update {}", user);
        userService.updateAuth(user);
        return ResponseEntity.noContent().build();
    }
}
