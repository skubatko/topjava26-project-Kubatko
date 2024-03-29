package ru.skubatko.dev.topjava.service.web.admin;

import ru.skubatko.dev.topjava.api.model.RoleTO;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.model.Role;
import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.util.JsonUtil;
import ru.skubatko.dev.topjava.service.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final MatcherFactory.Matcher<UserTO> USER_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserTO.class, "password", "roles");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int GUEST_ID = 3;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String GUEST_MAIL = "guest@mail.ru";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.USER, Role.ADMIN);
    public static final User guest = new User(GUEST_ID, "Guest", GUEST_MAIL, "guest", Role.USER);

    public static UserTO getUserTo() {
        return new UserTO().name("newName").email("newemail@ya.ru").password("newPassword")
                .enabled(true).roles(List.of(new RoleTO().role("ROLE_USER")));
    }

    public static UserCreateTO getCreateTo() {
        return new UserCreateTO().name("newName").email("newemail@ya.ru").password("newPassword")
                .enabled(true).roles(List.of(new RoleTO().role("ROLE_USER")));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static UserTO getUpdatedTo() {
        return new UserTO().id(USER_ID).name("newName").email(USER_MAIL).password("newPassword")
                .enabled(true).roles(List.of(new RoleTO().role("ROLE_USER")));
    }

    public static String jsonWithPassword(UserCreateTO user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }

    public static String jsonWithPassword(UserTO user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}
