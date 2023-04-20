package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FolderPersonPositionalRepository extends JpaRepository<FolderPersonPositional, Long>  {

    @Query("SELECT fpp FROM FolderPersonPositional fpp "
            + "JOIN FETCH fpp.folder f "
            + "JOIN FETCH fpp.person p "
            + "WHERE f.id = :folderId "
            + "AND p.id = :personId")
    FolderPersonPositional getByFolderIdAndPersonId(@Param("folderId") Long folderId, @Param("personId") Long personId);

    @Query("FROM FolderPersonPositional fpp "
            + "WHERE fpp.positional > :position "
            + "ORDER BY fpp.positional")
    List<FolderPersonPositional> getAllNextByPositionOrdered(@Param("position") Integer position);

    @Query("FROM FolderPersonPositional fpp "
            + "WHERE fpp.positional > :startPosition "
            + "AND fpp.positional <= :endPosition "
            + "ORDER BY fpp.positional")
    List<FolderPersonPositional> getAllBetweenTwoPositionsOrderedPositionIncreased(@Param("startPosition") Integer startPosition,
                                                                                   @Param("endPosition") Integer endPosition);

    @Query("FROM FolderPersonPositional fpp "
            + "WHERE fpp.positional < :startPosition "
            + "AND fpp.positional >= :endPosition "
            + "ORDER BY fpp.positional")
    List<FolderPersonPositional> getAllBetweenTwoPositionsOrderedPositionDecreased(@Param("startPosition") Integer startPosition,
                                                                                   @Param("endPosition") Integer endPosition);

    @Query("SELECT fpp.positional FROM FolderPersonPositional fpp ORDER BY fpp.id DESC LIMIT 1")
    Integer getLastPersonPosition();
}
