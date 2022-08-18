package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.model.Dish;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishTO toDto(Dish entity);

    Dish toEntity(DishTO dto);

    List<DishTO> toDtoList(List<Dish> entityList);
}
