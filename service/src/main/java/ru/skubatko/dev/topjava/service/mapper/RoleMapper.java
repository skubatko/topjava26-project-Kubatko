package ru.skubatko.dev.topjava.service.mapper;

import org.springframework.stereotype.Component;
import ru.skubatko.dev.topjava.api.model.RoleTO;
import ru.skubatko.dev.topjava.service.model.Role;

@Component
public class RoleMapper {

    public RoleTO toDto(Role role) {
        return new RoleTO().role(role.getAuthority());
    }

    public Role toEntity(RoleTO dto) {
        return Role.valueOf(dto.getRole());
    }
}
