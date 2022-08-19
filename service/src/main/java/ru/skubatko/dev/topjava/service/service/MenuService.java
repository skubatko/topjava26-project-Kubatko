package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.MenuItemCreateTO;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.mapper.MenuMapper;
import ru.skubatko.dev.topjava.service.model.Dish;
import ru.skubatko.dev.topjava.service.model.MenuItem;
import ru.skubatko.dev.topjava.service.model.Restaurant;
import ru.skubatko.dev.topjava.service.repository.DishRepository;
import ru.skubatko.dev.topjava.service.repository.MenuRepository;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.assureIdConsistent;
import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.checkNew;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuMapper mapper;

    public MenuItemTO get(Integer id) {
        log.info("get {}", id);
        return mapper.toDto(menuRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<MenuItemTO> getByParams(LocalDate day, Integer dishId, Integer restaurantId) {
        log.debug("getByParams() - start: day={}, dishId={}, restaurantId={}", day, dishId, restaurantId);
        Dish dish = dishId != null
                ? dishRepository.findById(dishId).orElseThrow(EntityNotFoundException::new)
                : null;
        Restaurant restaurant = restaurantId != null
                ? restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new)
                : null;
        Sort sortedBy = Sort.by(Sort.Direction.ASC, "restaurant", "dish");

        if (dish == null && restaurant == null) {
            List<MenuItemTO> result = mapper.toDtoList(menuRepository.findAllByDay(day, sortedBy));
            log.debug("getByParams() - end: result={}", result);
            return result;
        }

        if (dish != null && restaurant == null) {
            List<MenuItemTO> result = mapper.toDtoList(menuRepository.findAllByDayAndDish(day, dish, sortedBy));
            log.debug("getByParams() - end: result={}", result);
            return result;
        }

        if (dish == null) {
            List<MenuItemTO> result = mapper.toDtoList(menuRepository.findAllByDayAndRestaurant(day, restaurant, sortedBy));
            log.debug("getByParams() - end: result={}", result);
            return result;
        }

        List<MenuItemTO> result = mapper.toDtoList(
                menuRepository.findAllByDayAndDishAndRestaurant(day, dish, restaurant, sortedBy));
        log.debug("getByParams() - end: result={}", result);
        return result;
    }

    public List<MenuItemTO> getAll() {
        log.info("getAll");
        return mapper.toDtoList(menuRepository.findAll(Sort.by(Sort.Direction.ASC, "day")));
    }

    @Transactional
    public MenuItemTO create(MenuItemCreateTO dto) {
        log.info("create {}", dto);
        MenuItem menu = mapper.toEntity(dto);
        checkNew(menu);
        return mapper.toDto(menuRepository.save(menu));
    }

    @Transactional
    public void update(Integer id, MenuItemTO dto) {
        log.info("update {} with id={}", dto, id);
        MenuItem menu = mapper.toEntity(dto);
        assureIdConsistent(menu, id);
        mapper.toDto(menuRepository.save(menu));
    }

    @Transactional
    public void delete(Integer id) {
        log.info("delete {}", id);
        menuRepository.deleteExisted(id);
    }
}
