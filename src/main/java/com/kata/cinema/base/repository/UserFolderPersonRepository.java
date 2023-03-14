package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.entitys.FolderPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserFolderPersonRepository extends JpaRepository<FolderPerson, Long> {

    @Query("SELECT new com.kata.cinema.base.models.dto.response.FolderPersonResponseDto(" +
            "fp.id," +
            "fp.privacy," +
            "fp.name," +
            "fp.description," +
            "fp.folderPersonType) FROM FolderPerson fp WHERE fp.user.id = :userId")
    List<FolderPersonResponseDto> getFolderPersonByUserId(@Param("userId") Long userId);
}
