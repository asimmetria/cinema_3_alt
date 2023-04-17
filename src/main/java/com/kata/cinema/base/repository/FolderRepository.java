package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    Folder findFolderByUserId(Long id);
}
