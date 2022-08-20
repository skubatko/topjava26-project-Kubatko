package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.skubatko.dev.topjava.api.api.AdminRestaurantApi;
import ru.skubatko.dev.topjava.api.model.RestaurantCreateTO;
import ru.skubatko.dev.topjava.api.model.RestaurantTO;
import ru.skubatko.dev.topjava.service.service.RestaurantService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminRestaurantController implements AdminRestaurantApi {

    private final RestaurantService service;

    static final String REST_URL = "/api/admin/v1/restaurants";

    @Override
    public ResponseEntity<RestaurantTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<RestaurantTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<RestaurantTO> create(RestaurantCreateTO newRestaurant) {
        log.info("create {}", newRestaurant);
        RestaurantTO created = service.create(newRestaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, RestaurantTO restaurantTO) {
        service.update(id, restaurantTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
