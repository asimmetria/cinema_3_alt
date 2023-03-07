package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.FolderPersonResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFolderPersonRepository extends JpaRepository<FolderPersonResponseDto, Long> {
}
