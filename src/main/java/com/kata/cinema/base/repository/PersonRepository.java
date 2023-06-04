package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.FullNamePersonDto;
import com.kata.cinema.base.models.dto.request.SearchPersonDto;
import com.kata.cinema.base.models.dto.response.PersonInfoDto;
import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entitys.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT new com.kata.cinema.base.models.dto.response.PersonInfoDto(" +
        "p.id, " +
        "p.previewUrl, " +
        "CONCAT(p.firstName, ' ', p.lastName), " +
        "CONCAT(p.originalFirstName, ' ', p.originalLastName), " +
        "p.height, " +
        "p.dateBirth, " +
        "p.placeOfBirth) " +
        "FROM Person p " +
        "WHERE p.id = :personId")
    PersonInfoDto getPersonInfo(@Param("personId") Long personId);

    @Query("SELECT COUNT(m) " +
        "FROM Movie m " +
        "JOIN m.casts c " +
        "WHERE c.person.id = :personId")
    long getMovieCountForPerson(@Param("personId") Long personId);

    @Query("SELECT NEW com.kata.cinema.base.models.dto.response.GenreResponseDto(g.id, g.name) " +
        "FROM Movie m " +
        "JOIN m.casts c " +
        "JOIN m.genre g " +
        "WHERE c.person.id = :personId " +
        "GROUP BY g.id, g.name " +
        "ORDER BY COUNT(g) DESC")
    List<GenreResponseDto> getTopGenresForPerson(@Param("personId") Long personId);

    @Query(
        "SELECT NEW com.kata.cinema.base.models.dto.response.ProfessionResponseDto(c.profession.id, c.profession.name) "
            +
            "FROM Cast c " +
            "WHERE c.person.id = :personId")
    List<ProfessionResponseDto> getProfessionsForPerson(@Param("personId") Long personId);

    Person getPersonById(Long id);

    @Query("SELECT new com.kata.cinema.base.models.dto.request.SearchPersonDto(p.id, p.photoUrl, "
        + "CONCAT(p.firstName, ' ', p.lastName),"
        + " CONCAT(p.originalFirstName, ' ', p.originalLastName), p.dateBirth) "
        + "FROM Person p "
        + "WHERE CONCAT(p.firstName, ' ', p.lastName) "
        + "LIKE %:name%")
    List<SearchPersonDto> findByFullNameContaining(@Param("name") String name, Pageable pageable);

}