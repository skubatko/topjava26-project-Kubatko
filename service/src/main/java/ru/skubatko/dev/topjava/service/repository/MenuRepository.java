package ru.skubatko.dev.topjava.service.repository;

import org.springframework.data.domain.Sort;
import ru.skubatko.dev.topjava.service.model.Dish;
import ru.skubatko.dev.topjava.service.model.MenuItem;
import ru.skubatko.dev.topjava.service.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends BaseRepository<MenuItem> {

    List<MenuItem> findAllByDay(LocalDate day, Sort sortedBy);

    List<MenuItem> findAllByDayAndDish(LocalDate day, Dish dish, Sort sortedBy);

    List<MenuItem> findAllByDayAndRestaurant(LocalDate day, Restaurant restaurant, Sort sortedBy);

    List<MenuItem> findAllByDayAndDishAndRestaurant(LocalDate day, Dish dish, Restaurant restaurant, Sort sortedBy);
}
