package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.StudiosPerformanceRepository;
import com.kata.cinema.base.validation.StudiosPerformanceValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class StudiosPerformanceValidationImpl implements StudiosPerformanceValidation {

    private final StudiosPerformanceRepository performanceRepository;

    @Override
    public void isExistZeroPerformance(long id) {

        log.debug("Start validation = {}", id);

        if (performanceRepository.existsStudiosPerformanceByZero(id)) {

            log.error("Zero", id);
            throw new NotFoundEntityException(String.format("Равен нулю", id));
        }

        log.info("Success validation = {}", id);
    }
}
