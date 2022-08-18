package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.model.MenuItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuItemTO toDto(MenuItem entity);

    MenuItem toEntity(MenuItemTO dto);

    List<MenuItemTO> toDtoList(List<MenuItem> entityList);
}
