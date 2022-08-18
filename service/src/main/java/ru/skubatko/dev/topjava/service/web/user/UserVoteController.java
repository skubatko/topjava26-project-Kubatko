package ru.skubatko.dev.topjava.service.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skubatko.dev.topjava.api.api.UserVoteApi;
import ru.skubatko.dev.topjava.api.model.VoteTO;
import ru.skubatko.dev.topjava.service.service.VoteService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserVoteController implements UserVoteApi {

    private final VoteService voteService;

    @Override
    public ResponseEntity<Void> vote(VoteTO voteTO) {
        voteService.vote(voteTO);
        return ResponseEntity.noContent().build();
    }
}
