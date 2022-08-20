package ru.skubatko.dev.topjava.service.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skubatko.dev.topjava.service.service.VoteService;
import ru.skubatko.dev.topjava.service.util.JsonUtil;
import ru.skubatko.dev.topjava.service.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skubatko.dev.topjava.service.web.admin.UserTestData.GUEST_MAIL;
import static ru.skubatko.dev.topjava.service.web.admin.UserTestData.USER_MAIL;
import static ru.skubatko.dev.topjava.service.web.user.UserVoteController.REST_URL;
import static ru.skubatko.dev.topjava.service.web.user.VoteTestData.*;

class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteService voteService;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getDaily() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?day=2022-05-15"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_RESULT_TO_MATCHER.contentJson(getVoteResult()));
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void voteSuccess() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getGuestVote())))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_STATUS_TO_MATCHER.contentJson(getVoteStatusSuccess()));

        VOTE_RESULT_TO_MATCHER.assertMatch(voteService.getDaily(day), getVoteUpdatedResult());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void voteTooLate() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getGuestVote().votedAt(LocalTime.of(11, 0)))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_STATUS_TO_MATCHER.contentJson(getVoteStatusTooLate()));

        VOTE_RESULT_TO_MATCHER.assertMatch(voteService.getDaily(day), getVoteResult());
    }
}
