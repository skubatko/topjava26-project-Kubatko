package ru.skubatko.dev.topjava.service.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.service.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
}
