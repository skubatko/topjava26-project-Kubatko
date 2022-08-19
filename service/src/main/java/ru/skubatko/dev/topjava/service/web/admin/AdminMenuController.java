package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.AdminMenuApi;
import ru.skubatko.dev.topjava.api.model.MenuItemCreateTO;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.service.MenuService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMenuController implements AdminMenuApi {

    private final MenuService service;

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
    public ResponseEntity<Void> create(MenuItemCreateTO newMenuItem) {
        service.create(newMenuItem);
        return ResponseEntity.noContent().build();
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
