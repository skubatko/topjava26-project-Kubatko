package ru.skubatko.dev.topjava.service.mappers;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.admin.dto.restaurants.RestaurantTO;
import ru.skubatko.dev.topjava.service.model.Restaurant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    RestaurantTO restaurantToDto(Restaurant restaurant);

    List<RestaurantTO> restaurantsToDtoList(List<Restaurant> restaurants);
}
