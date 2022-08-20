package ru.skubatko.dev.topjava.service.web.user;

import ru.skubatko.dev.topjava.api.model.VoteResultTO;
import ru.skubatko.dev.topjava.api.model.VoteStatusTO;
import ru.skubatko.dev.topjava.api.model.VoteTO;
import ru.skubatko.dev.topjava.service.web.MatcherFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteResultTO> VOTE_RESULT_TO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(VoteResultTO.class);
    public static final MatcherFactory.Matcher<VoteStatusTO> VOTE_STATUS_TO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(VoteStatusTO.class);

    public static final LocalDate day = LocalDate.of(2022, 5, 15);

    public static VoteTO getGuestVote() {
        return new VoteTO().day(day).restaurantId(2).votedAt(LocalTime.of(10, 15));
    }

    public static VoteStatusTO getVoteStatusSuccess() {
        return new VoteStatusTO().status("SUCCESS");
    }

    public static VoteStatusTO getVoteStatusTooLate() {
        return new VoteStatusTO().status("TOO LATE");
    }

    public static List<VoteResultTO> getVoteResult() {
        return List.of(
                new VoteResultTO().restaurantId(1).votes(1),
                new VoteResultTO().restaurantId(3).votes(2)
        );
    }

    public static List<VoteResultTO> getVoteUpdatedResult() {
        return List.of(
                new VoteResultTO().restaurantId(1).votes(1),
                new VoteResultTO().restaurantId(2).votes(1),
                new VoteResultTO().restaurantId(3).votes(1)
        );
    }
}
