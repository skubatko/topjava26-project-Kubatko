package ru.skubatko.dev.topjava.service.mapper;

import org.mapstruct.Mapper;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.model.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserTO toDto(User user);

    List<UserTO> toDtoList(List<User> users);

    User toEntity(UserCreateTO dto);

    User toEntity(UserTO dto);
}
