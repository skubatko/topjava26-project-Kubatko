package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.UserCreateTO;
import ru.skubatko.dev.topjava.api.model.UserTO;
import ru.skubatko.dev.topjava.service.mapper.UserMapper;
import ru.skubatko.dev.topjava.service.model.User;
import ru.skubatko.dev.topjava.service.repository.UserRepository;
import ru.skubatko.dev.topjava.service.util.UserUtil;
import ru.skubatko.dev.topjava.service.web.SecurityUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.assureIdConsistent;
import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.checkNew;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "users")
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserTO get(Integer id) {
        log.info("get {}", id);
        return mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public UserTO getAuth() {
        log.info("getAuth");
        return mapper.toDto(SecurityUtil.authUser());
    }

    @Cacheable
    public List<UserTO> getAll() {
        log.info("getAll");
        return mapper.toDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email")));
    }

    public UserTO getByEmail(String email) {
        log.info("getByEmail {}", email);
        return mapper.toDto(repository.getByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void enable(Integer id, Boolean enabled) {
        log.info(Boolean.TRUE.equals(enabled) ? "enable {}" : "disable {}", id);
        User user = repository.getById(id);
        user.setEnabled(enabled);
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public UserTO create(UserCreateTO dto) {
        log.info("create {}", dto);
        User user = mapper.toEntity(dto);
        checkNew(user);
        return mapper.toDto(prepareAndSave(user));
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void update(Integer id, UserTO dto) {
        log.info("update {} with id={}", dto, id);
        User user = mapper.toEntity(dto);
        assureIdConsistent(user, id);
        prepareAndSave(user);
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void updateAuth(UserTO dto) {
        log.info("updateAuth {}", dto);
        User user = mapper.toEntity(dto);
        assureIdConsistent(user, SecurityUtil.authId());
        prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(UserUtil.prepareToSave(user));
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void delete(Integer id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void deleteAuth() {
        log.info("deleteAuth");
        repository.deleteExisted(SecurityUtil.authId());
    }
}
