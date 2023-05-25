package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.PersonInfoDto;
import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p.id, p.previewUrl, CONCAT(p.firstName, ' ', p.lastName), CONCAT(p.originalFirstName, ' ', p.originalLastName), p.height, p.dateBirth, p.placeOfBirth " +
            "FROM Person p " +
            "WHERE p.id = :personId")
    PersonInfoDto getPersonInfo(@Param("personId") Long personId);

    @Query("SELECT COUNT(m) " +
            "FROM Movie m " +
            "JOIN m.cast c " +
            "WHERE c.person.id = :personId")
    long getMovieCountForPerson(@Param("personId") Long personId);

    @Query("SELECT NEW com.kata.cinema.base.models.dto.response.GenreResponseDto(g.name) " +
            "FROM Movie m " +
            "JOIN m.cast c " +
            "JOIN m.genre g " +
            "WHERE c.person.id = :personId " +
            "GROUP BY g.name " +
            "ORDER BY COUNT(g) DESC " +
            "LIMIT 3")
    List<GenreResponseDto> getTopGenresForPerson(@Param("personId") Long personId);

    @Query("SELECT NEW com.kata.cinema.base.models.dto.response.ProfessionResponseDto(c.profession.name) " +
            "FROM Cast c " +
            "WHERE c.person.id = :personId")
    List<ProfessionResponseDto> getProfessionsForPerson(@Param("personId") Long personId);

    Person getPersonById(Long id);
}
