package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FolderMoviePositionalServiceImpl implements FolderMoviePositionalService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;
    private final FolderRepository folderRepository;
    private final MovieRepository movieRepository;

    @Override
    public void save(Long folderId, Long movieId) {
        FolderMoviePositional folderMoviePositional = new FolderMoviePositional();
        folderMoviePositional.setFolder((FolderMovie) folderRepository.findById(folderId).orElseThrow(
                () -> new NotFoundEntityException("Папка не существует")));
        folderMoviePositional.setMovie(movieRepository.findById(movieId).orElseThrow(
                () -> new NotFoundEntityException("Фильм не существует")));
        folderMoviePositional.setPositional(folderMoviePositionalRepository.getLastMoviePosition() + 1);
        folderMoviePositionalRepository.save(folderMoviePositional);
    }


    @Override
    public void update(Long folderId, Long movieId, Integer newPosition) {
        FolderMoviePositional folderMoviePositional =
                folderMoviePositionalRepository.getByFolderIdAndMovieId(folderId, movieId);
        Integer oldPosition = folderMoviePositional.getPositional();

        if (oldPosition > newPosition) {
            folderMoviePositionalRepository.getAllBetweenTwoPositionsOrderedPositionDecreased(oldPosition, newPosition)
                    .forEach(fmp -> {
                        fmp.setPositional(fmp.getPositional() + 1);
                        folderMoviePositionalRepository.save(fmp);
                    });
        } else {
            folderMoviePositionalRepository.getAllBetweenTwoPositionsOrderedPositionIncreased(oldPosition, newPosition)
                    .forEach(fmp -> {
                        fmp.setPositional(fmp.getPositional() - 1);
                        folderMoviePositionalRepository.save(fmp);
                    });
        }

        folderMoviePositional.setPositional(newPosition);
        folderMoviePositionalRepository.save(folderMoviePositional);
    }

    @Override
    public void deleteById(Long id) {
        Integer deletedRecordPosition = folderMoviePositionalRepository.findById(id).orElseThrow(
                () -> new NotFoundEntityException("FolderMoviePositional не существует")).getPositional();
        folderMoviePositionalRepository.deleteById(id);
        List<FolderMoviePositional> nextRecords =
                folderMoviePositionalRepository.getAllNextByPositionOrdered(deletedRecordPosition);
        nextRecords.forEach(fmp -> {
            fmp.setPositional(fmp.getPositional() - 1);
            folderMoviePositionalRepository.save(fmp);
        });
    }

    @Override
    public FolderMoviePositional getByFolderIdAndMovieId(Long folderId, Long movieId) {
        return folderMoviePositionalRepository.getByFolderIdAndMovieId(folderId, movieId);
    }


}
