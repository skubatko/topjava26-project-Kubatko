package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.VoteResultTO;
import ru.skubatko.dev.topjava.api.model.VoteStatusTO;
import ru.skubatko.dev.topjava.api.model.VoteTO;
import ru.skubatko.dev.topjava.service.config.AppProps;
import ru.skubatko.dev.topjava.service.model.Restaurant;
import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.model.Vote;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;
import ru.skubatko.dev.topjava.service.repository.UserRepository;
import ru.skubatko.dev.topjava.service.repository.VoteRepository;
import ru.skubatko.dev.topjava.service.web.SecurityUtil;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final AppProps appProps;

    public List<VoteResultTO> getDaily(LocalDate day) {
        log.debug("getDaily() - start: day={}", day);
        Map<Integer, Long> restaurantIdCounts = voteRepository.findAllByDay(day).stream()
                .collect(Collectors.groupingBy(vote -> vote.getRestaurant().getId(), Collectors.counting()));
        List<VoteResultTO> result = new ArrayList<>();
        restaurantIdCounts.forEach((restaurantId, count) ->
                result.add(new VoteResultTO().restaurantId(restaurantId).votes(count.intValue())));
        log.debug("getDaily() - end: result={}", result);
        return result;
    }

    @Transactional
    public VoteStatusTO vote(VoteTO voteTO) {
        log.debug("vote() - start: voteTO={}", voteTO);
        LocalTime votedAt = voteTO.getVotedAt();
        LocalTime voteDeadlineTime = appProps.getVoteDeadlineTime();
        if (votedAt.equals(voteDeadlineTime) || votedAt.isAfter(voteDeadlineTime)) {
            VoteStatusTO status = new VoteStatusTO().status("TOO LATE");
            log.debug("vote() - end: status={}", status);
            return status;
        }

        User user = userRepository.findById(SecurityUtil.get().id())
                .orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(voteTO.getRestaurantId())
                .orElseThrow(EntityNotFoundException::new);
        LocalDate day = voteTO.getDay();
        Vote vote = voteRepository.findByUserAndDay(user, day)
                .orElseGet(() -> new Vote(day, user, restaurant));
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
        VoteStatusTO status = new VoteStatusTO().status("SUCCESS");
        log.debug("vote() - end: status={}", status);
        return status;
    }
}
