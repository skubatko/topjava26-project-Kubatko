package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.MenuItemTO;
import ru.skubatko.dev.topjava.service.mapper.MenuMapper;
import ru.skubatko.dev.topjava.service.model.MenuItem;
import ru.skubatko.dev.topjava.service.repository.MenuRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.assureIdConsistent;
import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.checkNew;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository repository;
    private final MenuMapper mapper;

    public MenuItemTO get(Integer id) {
        log.info("get {}", id);
        return mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<MenuItemTO> getAll() {
        log.info("getAll");
        return mapper.toDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email")));
    }

    @Transactional
    public MenuItemTO create(MenuItemTO dto) {
        log.info("create {}", dto);
        MenuItem menu = mapper.toEntity(dto);
        checkNew(menu);
        return mapper.toDto(repository.save(menu));
    }

    @Transactional
    public void update(Integer id, MenuItemTO dto) {
        log.info("update {} with id={}", dto, id);
        MenuItem menu = mapper.toEntity(dto);
        assureIdConsistent(menu, id);
        mapper.toDto(repository.save(menu));
    }

    @Transactional
    public void delete(Integer id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
