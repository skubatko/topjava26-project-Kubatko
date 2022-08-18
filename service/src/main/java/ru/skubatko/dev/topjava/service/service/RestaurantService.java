package ru.skubatko.dev.topjava.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skubatko.dev.topjava.api.model.RestaurantTO;
import ru.skubatko.dev.topjava.service.mapper.RestaurantMapper;
import ru.skubatko.dev.topjava.service.model.Restaurant;
import ru.skubatko.dev.topjava.service.repository.RestaurantRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.assureIdConsistent;
import static ru.skubatko.dev.topjava.service.util.validation.ValidationUtil.checkNew;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    public RestaurantTO get(Integer id) {
        log.info("get {}", id);
        return mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<RestaurantTO> getAll() {
        log.info("getAll");
        return mapper.toDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @Transactional
    public RestaurantTO create(RestaurantTO dto) {
        log.info("create {}", dto);
        Restaurant restaurant = mapper.toEntity(dto);
        checkNew(restaurant);
        return mapper.toDto(repository.save(restaurant));
    }

    @Transactional
    public void update(Integer id, RestaurantTO dto) {
        log.info("update {} with id={}", dto, id);
        Restaurant restaurant = mapper.toEntity(dto);
        assureIdConsistent(restaurant, id);
        mapper.toDto(repository.save(restaurant));
    }

    @Transactional
    public void delete(Integer id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
