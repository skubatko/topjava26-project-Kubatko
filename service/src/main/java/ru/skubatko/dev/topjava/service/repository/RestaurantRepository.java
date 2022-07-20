package ru.skubatko.dev.topjava.service.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.service.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
}
