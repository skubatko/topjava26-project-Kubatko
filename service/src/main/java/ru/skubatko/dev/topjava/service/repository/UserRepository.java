package ru.skubatko.dev.topjava.service.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.service.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}
