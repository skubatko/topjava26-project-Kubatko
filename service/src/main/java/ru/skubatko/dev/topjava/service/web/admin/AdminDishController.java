package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.AdminDishApi;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.mapper.RestaurantMapper;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminDishController implements AdminDishApi {

    private final RestaurantMapper mapper;
    private final RestaurantRepository repository;

    @Override
    public ResponseEntity<Void> create(DishTO dishTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<DishTO> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DishTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> update(Integer id, DishTO dishTO) {
        return null;
    }
}
