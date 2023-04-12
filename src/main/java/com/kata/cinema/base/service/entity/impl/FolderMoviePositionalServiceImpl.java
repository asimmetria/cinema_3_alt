package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FolderMoviePositionalServiceImpl implements FolderMoviePositionalService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;
    private final FolderRepository folderRepository;
    private final MovieRepository movieRepository;

    @Override
    public void save(Long folderId, Long movieId) {
        try {
            FolderMoviePositional folderMoviePositional = new FolderMoviePositional();
            folderMoviePositional.setFolder((FolderMovie) folderRepository.findById(folderId).orElseThrow(EntityNotFoundException::new));
            folderMoviePositional.setMovie(movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new));
            folderMoviePositional.setPositional(folderMoviePositionalRepository.getLastMoviePosition() + 1);
            folderMoviePositionalRepository.save(folderMoviePositional);
        } catch (NotFoundEntityException e) {
            throw new RuntimeException("Failed to save FolderMoviePositional. Folder with id " + folderId + " not found.", e);
        }
    }


    @Override
    public void update(Long folderId, Long movieId, Integer newPosition) {

        FolderMoviePositionalResponseDto folderMoviePositionalResponse =
                folderMoviePositionalRepository.getByFolderIdAndMovieId(folderId, movieId);
        Optional<FolderMoviePositional> folderMoviePositionalOptional =
                folderMoviePositionalRepository.findById(folderMoviePositionalResponse.getId());

        if (folderMoviePositionalOptional.isPresent()) {
            folderMoviePositionalRepository.findAll()
                    .stream()
                    .filter(x -> x.getPositional() >= newPosition)
                    .forEach(x -> {
                        Optional<FolderMoviePositional> fmpOptional = folderMoviePositionalRepository.findById(x.getId());
                        if (fmpOptional.isPresent()) {
                            FolderMoviePositional fmp = fmpOptional.get();
                            fmp.setPositional(x.getPositional() + 1);
                            folderMoviePositionalRepository.save(fmp);
                        }
                    });

                FolderMoviePositional folderMoviePositional = folderMoviePositionalOptional.get();
                folderMoviePositional.setPositional(newPosition);
                folderMoviePositionalRepository.save(folderMoviePositional);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<FolderMoviePositional> folderMoviePositionalOptional = folderMoviePositionalRepository.findById(id);
        if (folderMoviePositionalOptional.isPresent()) {
            folderMoviePositionalRepository.deleteById(id);
            FolderMoviePositional folderMoviePositional = folderMoviePositionalOptional.get();
            folderMoviePositionalRepository.findAll().stream()
                    .filter(x -> x.getPositional() > folderMoviePositional.getPositional())
                    .forEach(x -> {
                        Optional<FolderMoviePositional> fmpOptional = folderMoviePositionalRepository.findById(x.getId());
                        if (fmpOptional.isPresent()) {
                            FolderMoviePositional fmp = fmpOptional.get();
                            fmp.setPositional(x.getPositional() - 1);
                            folderMoviePositionalRepository.save(fmp);
                        }
                    });
        }

    }

}
