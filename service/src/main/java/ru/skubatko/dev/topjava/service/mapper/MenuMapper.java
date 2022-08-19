package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skubatko.dev.topjava.api.model.MenuItemCreateTO;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.model.MenuItem;
import ru.skubatko.dev.topjava.service.repository.DishRepository;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class MenuMapper {
    @Autowired
    protected DishRepository dishRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Mapping(source = "dish.id", target = "dishId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    public abstract MenuItemTO toDto(MenuItem entity);

    public abstract List<MenuItemTO> toDtoList(List<MenuItem> entityList);

    @Mapping(target = "dish", expression = "java(dishRepository.findById(dto.getDishId()).orElseThrow(javax.persistence.EntityNotFoundException::new))")
    @Mapping(target = "restaurant", expression = "java(restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(javax.persistence.EntityNotFoundException::new))")
    public abstract MenuItem toEntity(MenuItemTO dto);

    @Mapping(target = "dish", expression = "java(dishRepository.findById(dto.getDishId()).orElseThrow(javax.persistence.EntityNotFoundException::new))")
    @Mapping(target = "restaurant", expression = "java(restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(javax.persistence.EntityNotFoundException::new))")
    public abstract MenuItem toEntity(MenuItemCreateTO dto);
}
