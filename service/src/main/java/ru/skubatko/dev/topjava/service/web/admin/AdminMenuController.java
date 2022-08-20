package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.skubatko.dev.topjava.api.api.AdminMenuApi;
import ru.skubatko.dev.topjava.api.model.MenuItemCreateTO;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.service.MenuService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMenuController implements AdminMenuApi {

    private final MenuService service;

    static final String REST_URL = "/api/admin/v1/menu";

    @Override
    public ResponseEntity<MenuItemTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<MenuItemTO>> getByParams(LocalDate day, Integer dishId, Integer restaurantId) {
        return ResponseEntity.ok(service.getByParams(day, dishId, restaurantId));
    }

    @Override
    public ResponseEntity<List<MenuItemTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<MenuItemTO> create(MenuItemCreateTO newMenuItem) {
        log.info("create {}", newMenuItem);
        MenuItemTO created = service.create(newMenuItem);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, MenuItemTO menuItemTO) {
        service.update(id, menuItemTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
