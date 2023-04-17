package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {


    @Query("SELECT  new com.kata.cinema.base.models.dto.response.CollectionResponseDto(" +
            "c.id," +
            "c.name," +
            "c.collectionUrl, count(f.movie)) from Collection c," +
            "FolderMoviePositional f where f.folder.folderMovieType = 'VIEWED_MOVIES' " +
            "and f.folder.user.id = :userId " +
            "group by c.id " +
            "order by c.id")
    List<CollectionResponseDto> getAllCollections(@Param("userId") Long userId);

    @Query("SELECT  new com.kata.cinema.base.models.dto.response.CollectionResponseDto(" +
            "c.id," +
            "c.name," +
            "c.collectionUrl,  count(f.movie)) from Collection c, " +
            "FolderMoviePositional f where f.folder.folderMovieType =  'VIEWED_MOVIES' " +
            "and f.folder.user.id = :userId " +
            "and c.category.id = :categoryId " +
            "group by c.id " +
            "order by c.id")
    List<CollectionResponseDto> getCollectionsByCategoryId(@Param("categoryId") Long categoryId,
                                                           @Param("userId") Long userId);

    Collection getCollectionById(Long id);
}
