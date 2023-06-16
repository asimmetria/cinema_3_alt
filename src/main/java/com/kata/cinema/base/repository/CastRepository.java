package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<Cast, Long> {

    boolean existsByProfessionId(Long professionId);

}
