package ru.skubatko.dev.topjava.service.repository;

import ru.skubatko.dev.topjava.service.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}
