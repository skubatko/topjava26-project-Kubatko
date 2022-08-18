package ru.skubatko.dev.topjava.service.repository;

import ru.skubatko.dev.topjava.service.model.Restaurant;
import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.model.Vote;

import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote> {
    Optional<Vote> findByUserAndRestaurant(User user, Restaurant restaurant);
}
