package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FolderMoviePositionalServiceImpl implements FolderMoviePositionalService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;
    private final FolderRepository folderRepository;
    private final MovieRepository movieRepository;

    @Override
    public void save(Long folderId, Long movieId) {
        FolderMoviePositional folderMoviePositional = new FolderMoviePositional();
        folderMoviePositional.setFolder((FolderMovie) folderRepository.getReferenceById(folderId));
        folderMoviePositional.setMovie(movieRepository.getReferenceById(movieId));
        folderMoviePositional.setPositional(folderMoviePositionalRepository.getLastMoviePosition() + 1);
        folderMoviePositionalRepository.save(folderMoviePositional);
    }


    @Override
    public void update(Long folderId, Long movieId, Integer newPosition) {
        FolderMoviePositionalResponseDto folderMoviePositionalResponse =
                folderMoviePositionalRepository.getByFolderIdAndMovieId(folderId, movieId);
        FolderMoviePositional folderMoviePositional = new FolderMoviePositional();
        folderMoviePositional.setFolder((FolderMovie) folderMoviePositionalResponse.getFolder());
        folderMoviePositional.setMovie(folderMoviePositionalResponse.getMovie());
        folderMoviePositional.setPositional(newPosition);

        folderMoviePositionalRepository.findAll()
                .stream()
                .filter(x -> x.getPositional() > newPosition)
                .map(x -> {
                    FolderMoviePositional fmp = new FolderMoviePositional();
                    fmp.setPositional(x.getPositional() + 1);
                    fmp.setMovie(x.getMovie());
                    fmp.setFolder(x.getFolder());
                    return fmp;
                }).forEach(folderMoviePositionalRepository::save);
        folderMoviePositionalRepository.save(folderMoviePositional);
    }

    @Override
    public void deleteById(Long id) {
        FolderMoviePositional folderMoviePositional = folderMoviePositionalRepository.getReferenceById(id);
        folderMoviePositionalRepository.deleteById(id);
        folderMoviePositionalRepository.findAll().stream()
                .filter(x -> x.getPositional() > folderMoviePositional.getPositional())
                .map(x -> {
                    FolderMoviePositional fmp = new FolderMoviePositional();
                    fmp.setPositional(x.getPositional() - 1);
                    fmp.setMovie(x.getMovie());
                    fmp.setFolder(x.getFolder());
                    return fmp;
                }).forEach(folderMoviePositionalRepository::save);
    }

    @Override
    public FolderMoviePositional getProxyById(Long id) {
        return folderMoviePositionalRepository.getReferenceById(id);
    }



}
