package ru.skubatko.dev.topjava.service.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.AdminRestaurantApi;
import ru.skubatko.dev.topjava.api.model.RestaurantCreateTO;
import ru.skubatko.dev.topjava.api.model.RestaurantTO;
import ru.skubatko.dev.topjava.service.service.RestaurantService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminRestaurantController implements AdminRestaurantApi {

    private final RestaurantService service;

    @Override
    public ResponseEntity<RestaurantTO> get(Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<RestaurantTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<Void> create(RestaurantCreateTO newRestaurant) {
        service.create(newRestaurant);
        return ResponseEntity.noContent().build();
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
