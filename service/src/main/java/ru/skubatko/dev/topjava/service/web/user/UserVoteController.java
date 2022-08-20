package ru.skubatko.dev.topjava.service.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.UserVoteApi;
import ru.skubatko.dev.topjava.api.model.VoteResultTO;
import ru.skubatko.dev.topjava.api.model.VoteStatusTO;
import ru.skubatko.dev.topjava.api.model.VoteTO;
import ru.skubatko.dev.topjava.service.service.VoteService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserVoteController implements UserVoteApi {

    private final VoteService voteService;

    static final String REST_URL = "/api/user/v1/votes";

    @Override
    public ResponseEntity<List<VoteResultTO>> getDaily(LocalDate day) {
        return ResponseEntity.ok(voteService.getDaily(day));
    }

    @Override
    public ResponseEntity<VoteStatusTO> vote(VoteTO voteTO) {
        return ResponseEntity.ok(voteService.vote(voteTO));
    }
}
