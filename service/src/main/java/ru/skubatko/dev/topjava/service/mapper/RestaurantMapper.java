package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.model.RestaurantCreateTO;
import ru.skubatko.dev.topjava.api.model.RestaurantTO;
import ru.skubatko.dev.topjava.service.model.Restaurant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantTO toDto(Restaurant entity);

    List<RestaurantTO> toDtoList(List<Restaurant> entityList);

    Restaurant toEntity(RestaurantCreateTO dto);

    Restaurant toEntity(RestaurantTO dto);
}
