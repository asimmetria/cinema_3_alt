package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.FolderMovie;
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
            "c.collectionUrl, count(f.folderMovieType)) from Collection c," +
            "FolderMovie f where cast(f.folderMovieType as string) like 'VIEWED_MOVIES'")
    List<CollectionResponseDto> getAllCollections();

    @Query("SELECT  new com.kata.cinema.base.models.dto.response.CollectionResponseDto(" +
            "c.id," +
            "c.name," +
            "c.collectionUrl,  count(f.folderMovieType)) from Collection c, " +
            "FolderMovie f where cast(f.folderMovieType as string) like 'VIEWED_MOVIES'" +
            "and c.category.id = :categoryId")
    List<CollectionResponseDto> getCollectionsByCategoryId(@Param("categoryId") Long categoryId);

    Collection getCollectionById(Long id);
}
