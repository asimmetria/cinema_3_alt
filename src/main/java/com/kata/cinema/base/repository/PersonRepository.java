package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT NEW com.kata.cinema.base.models.dto.response.PersonViewResponseDto(p.id, p.previewUrl, CONCAT(p.firstName, ' ', p.lastName), CONCAT(p.originalFirstName, ' ', p.originalLastName), p.height, " +
            "(SELECT COUNT(m) FROM Movie m JOIN m.casts c WHERE c.person.id = p.id), " +
            "(SELECT NEW com.kata.cinema.base.models.dto.response.GenreResponseDto(g.genreName) FROM Movie m JOIN m.casts c JOIN m.genres g WHERE c.person.id = p.id GROUP BY g.genreName ORDER BY COUNT(g) DESC LIMIT 3), " +
            "(SELECT NEW com.kata.cinema.base.models.dto.response.ProfessionResponseDto(c.profession.professionName) FROM Cast c WHERE c.person.id = p.id), " +
            "c.profession.professionName) " +
            "FROM Person p " +
            "LEFT JOIN p.casts c " +
            "LEFT JOIN c.movie m " +
            "WHERE p.id = :personId " +
            "GROUP BY p.id, c.profession.professionName")
    PersonViewResponseDto getPersonViewById(@Param("personId") Long personId);

    Person getPersonById(Long id);
}
