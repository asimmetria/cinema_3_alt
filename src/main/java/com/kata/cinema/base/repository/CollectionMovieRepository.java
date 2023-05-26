package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.CollectionMovie;
import com.kata.cinema.base.models.entitys.Movie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionMovieRepository extends JpaRepository<CollectionMovie, Long> {
    List<CollectionMovie> findByCollectionAndMovieIn(Collection collection, List<Movie> movies);

    List<CollectionMovie> getCollectionMovieByCollection_Id(Long Id);


    @EntityGraph(value = "collectionMovieGraph")
    @Query("select cm from CollectionMovie cm inner join Movie m on cm.movie.id = m.id where cm.collection.id = :id " +
            "and ( :dataRel is null or m.dateRelease > :dataRel) ")
    List<CollectionMovie> getCollectionMovieById(@Param("id") Long id,
                                                 @Param("dataRel") LocalDate dataRelease);


    //(cast(:dataRel as date) is null )
    //:dataRel::timestamp

    //   @EntityGraph(value = "collectionMovieGraph")
    //    @Query("select cm from CollectionMovie cm where cm.collection.id = :id " +
    //            "and ( :dataRel is null or cm.movie.dateRelease > :dataRel) ")
    //    List<CollectionMovie> getCollectionMovieById(@Param("id") Long id,
    //                                                 @Param("dataRel") LocalDate dataRelease);

}

