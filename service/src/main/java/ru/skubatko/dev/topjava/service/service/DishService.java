package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.DishTO;
import ru.skubatko.dev.topjava.service.mapper.DishMapper;
import ru.skubatko.dev.topjava.service.model.Dish;
import ru.skubatko.dev.topjava.service.repository.DishRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.assureIdConsistent;
import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.checkNew;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DishService {
    private final DishRepository repository;
    private final DishMapper mapper;

    public DishTO get(Integer id) {
        log.info("get {}", id);
        return mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<DishTO> getAll() {
        log.info("getAll");
        return mapper.toDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @Transactional
    public DishTO create(DishTO dto) {
        log.info("create {}", dto);
        Dish dish = mapper.toEntity(dto);
        checkNew(dish);
        return mapper.toDto(repository.save(dish));
    }

    @Transactional
    public void update(Integer id, DishTO dto) {
        log.info("update {} with id={}", dto, id);
        Dish dish = mapper.toEntity(dto);
        assureIdConsistent(dish, id);
        mapper.toDto(repository.save(dish));
    }

    @Transactional
    public void delete(Integer id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
