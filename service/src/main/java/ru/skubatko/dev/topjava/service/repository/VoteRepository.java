package ru.skubatko.dev.topjava.service.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.service.model.Vote;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
}
