package ru.skubatko.dev.topjava.service.mappers;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.admin.dto.RestaurantsTO;
import ru.skubatko.dev.topjava.service.model.Restaurant;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    RestaurantsTO restaurantToDto(Restaurant restaurant);

    List<RestaurantsTO> restaurantsToDtoList(List<Restaurant> restaurants);
}
