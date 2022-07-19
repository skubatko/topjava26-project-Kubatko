package ru.skubatko.dev.topjava.service.web;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import ru.skubatko.dev.topjava.service.model.User;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
