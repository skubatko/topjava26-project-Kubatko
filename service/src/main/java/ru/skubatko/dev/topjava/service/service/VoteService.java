package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.user.dto.VoteTO;
import ru.skubatko.dev.topjava.service.config.AppProps;
import ru.skubatko.dev.topjava.service.model.Restaurant;
import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.model.Vote;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;
import ru.skubatko.dev.topjava.service.repository.VoteRepository;
import ru.skubatko.dev.topjava.service.web.SecurityUtil;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final AppProps appProps;

    @Transactional
    public void vote(VoteTO voteTO) {
        LocalTime votedAt = voteTO.getVotedAt();
        LocalTime voteDeadlineTime = appProps.getVoteDeadlineTime();
        if (votedAt.equals(voteDeadlineTime) || votedAt.isAfter(voteDeadlineTime)) {
            return;
        }

        User user = SecurityUtil.get().getUser();
        Restaurant restaurant = restaurantRepository.findById(voteTO.getRestaurantId())
                .orElseThrow(EntityNotFoundException::new);
        Vote vote = voteRepository.findByUserAndRestaurant(user, restaurant)
                .orElseGet(() -> new Vote(user, restaurant));
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
    }
}
