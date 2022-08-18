package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.AdminMenuApi;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.mapper.RestaurantMapper;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMenuController implements AdminMenuApi {

    private final RestaurantMapper mapper;
    private final RestaurantRepository repository;

    @Override
    public ResponseEntity<Void> create(MenuItemTO menuItemTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<MenuItemTO> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<MenuItemTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<List<MenuItemTO>> getByParams(String day, Integer dishId, Integer restaurantId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> update(Integer id, MenuItemTO menuItemTO) {
        return null;
    }
}
