package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {


    @Query("SELECT  new com.kata.cinema.base.models.dto.response.CollectionResponseDto(" +
            "c.id," +
            "c.name," +
            "c.collectionUrl," +
            "(select count(cm.movie) from CollectionMovie cm join FolderMoviePositional fmp " +
            "join cm.movie on cm.movie.id = fmp.movie.id and cm.collection.id = c.id " +
            "where  fmp.folder.folderMovieType = 'VIEWED_MOVIES' and fmp.folder.user.id = :userId)) " +
            "from Collection c")
    List<CollectionResponseDto> getAllCollections(@Param("userId") Long userId);

    @Query("SELECT  new com.kata.cinema.base.models.dto.response.CollectionResponseDto(" +
            "c.id," +
            "c.name," +
            "c.collectionUrl," +
            "(select count(cm.movie) from CollectionMovie cm  join FolderMoviePositional fmp " +
            "join cm.movie on cm.movie.id = fmp.movie.id and cm.collection.id = c.id " +
            "where  fmp.folder.folderMovieType = 'VIEWED_MOVIES' and fmp.folder.user.id = :userId)) " +
            "from Collection c " +
            "where c.category.id = :categoryId ")
    List<CollectionResponseDto> getCollectionsByCategoryId(@Param("categoryId") Long categoryId,
                                                           @Param("userId") Long userId);

    Collection getCollectionById(Long id);

    @Modifying
    @Query("UPDATE Collection c SET c.enable = :value WHERE c.id = :id")
    void deactivateById(@Param("id") Long id, @Param("value") boolean value);

    @Modifying
    @Query("UPDATE Collection c SET c.enable = :value WHERE c.id = :id")
    void activateById(@Param("id") Long id, @Param("value") boolean value);

    @EntityGraph(attributePaths = {"movies"})
    List<Collection> findByNameContaining(String name);

}
