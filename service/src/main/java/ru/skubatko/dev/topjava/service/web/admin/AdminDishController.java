package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.AdminDishApi;
import ru.skubatko.dev.topjava.api.model.DishCreateTO;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.service.DishService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminDishController implements AdminDishApi {

    private final DishService service;

    @Override
    public ResponseEntity<DishTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<DishTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<Void> create(DishCreateTO newDish) {
        service.create(newDish);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> update(Integer id, DishTO dishTO) {
        service.update(id, dishTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
