package ru.skubatko.dev.topjava.service.repository;

import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote> {

    Optional<Vote> findByUserAndDay(User user, LocalDate day);

    List<Vote> findAllByDay(LocalDate day);
}
