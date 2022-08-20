package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.skubatko.dev.topjava.api.api.AdminDishApi;
import ru.skubatko.dev.topjava.api.model.DishCreateTO;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.service.DishService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminDishController implements AdminDishApi {

    private final DishService service;

    static final String REST_URL = "/api/admin/v1/dishes";

    @Override
    public ResponseEntity<DishTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<DishTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<DishTO> create(DishCreateTO newDish) {
        log.info("create {}", newDish);
        DishTO created = service.create(newDish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
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
