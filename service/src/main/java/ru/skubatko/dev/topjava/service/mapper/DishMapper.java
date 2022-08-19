package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.model.DishCreateTO;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.model.Dish;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishTO toDto(Dish entity);

    List<DishTO> toDtoList(List<Dish> entityList);

    Dish toEntity(DishTO dto);

    Dish toEntity(DishCreateTO dto);
}
