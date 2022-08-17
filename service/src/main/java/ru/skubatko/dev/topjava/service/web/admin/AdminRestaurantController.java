package ru.skubatko.dev.topjava.service.web.admin;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.admin.api.restaurants.AdminRestaurantApi;
import ru.skubatko.dev.topjava.api.admin.dto.restaurants.RestaurantTO;
import ru.skubatko.dev.topjava.service.mappers.MapStructMapper;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminRestaurantController implements AdminRestaurantApi {

    private final MapStructMapper mapper;
    private final RestaurantRepository repository;

    @Override
    public ResponseEntity<Void> create(RestaurantTO restaurantTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<RestaurantTO> get(
            @Min(0) @Max(2147483647) @ApiParam(value = "Идентификатор ресторана", required = true)
            @PathVariable("restaurantId") Integer restaurantId) {
        log.info("get");
        return ResponseEntity.ok(mapper.restaurantToDto(repository.findById(restaurantId).orElseThrow()));
    }

    @Override
    public ResponseEntity<List<RestaurantTO>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(mapper.restaurantsToDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "name"))));
    }

    @Override
    public ResponseEntity<Void> update(Integer id, RestaurantTO restaurantTO) {
        return null;
    }
}
