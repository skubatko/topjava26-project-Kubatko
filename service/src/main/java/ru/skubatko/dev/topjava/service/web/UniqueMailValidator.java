package ru.skubatko.dev.topjava.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class UniqueMailValidator implements Validator {

    private final UserRepository repository;
    private final HttpServletRequest request;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserCreateTO.class.isAssignableFrom(clazz) || UserTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        if (target instanceof UserCreateTO user) {
            if (StringUtils.hasText(user.getEmail())) {
                repository.getByEmail(user.getEmail().toLowerCase())
                        .ifPresent(dbUser -> errors.rejectValue("email", "", GlobalExceptionHandler.EXCEPTION_DUPLICATE_EMAIL));
            }
            return;
        }

        UserTO user = ((UserTO) target);
        if (StringUtils.hasText(user.getEmail())) {
            repository.getByEmail(user.getEmail().toLowerCase())
                    .ifPresent(dbUser -> {
                        int dbId = dbUser.id();

                        // it is ok, if update ourselves
                        if (user.getId() != null && dbId == user.getId()) return;

                        // Workaround for update with user.id=null in request body
                        // ValidationUtil.assureIdConsistent called after this validation
                        String requestURI = request.getRequestURI();
                        if (requestURI.endsWith("/" + dbId)
                                || (dbId == SecurityUtil.authId() && requestURI.contains("/profile"))) {
                            return;
                        }
                        errors.rejectValue("email", "", GlobalExceptionHandler.EXCEPTION_DUPLICATE_EMAIL);
                    });
        }
    }
}
