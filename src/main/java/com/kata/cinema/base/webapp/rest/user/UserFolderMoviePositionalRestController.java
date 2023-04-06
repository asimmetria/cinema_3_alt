package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.dto.request.FolderMoviePositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/api/user/folders")
@RequiredArgsConstructor
public class UserFolderMoviePositionalRestController {

    private final UserFolderMoviePositionalServiceFacade userMoviePositionalServiceFacade;

    @PostMapping("/{id}/movies/{movieId}")
    public ResponseEntity<Void> addMovieToFolder(@PathVariable Long id,
                                                 @PathVariable Long movieId,
                                                 @Valid @RequestBody FolderMoviePositionalRequestDto folderMoviePositionalRequestDto) {

        // Сортировка полученных записей из БД (FolderMoviePositional) по возрастанию позиций
        List<FolderMoviePositionalResponseDto> allRecords = userMoviePositionalServiceFacade
                .getAll()
                .stream()
                .sorted(Comparator.comparing(FolderMoviePositionalResponseDto::getPositional))
                .toList();

        // Получение позиции последнего добавленного фильма + 1 для заполнения поля positional добавленного фильма
        Integer newMoviePosition = allRecords.get(allRecords.size() - 1).getPositional() + 1;
        folderMoviePositionalRequestDto.setPositional(newMoviePosition);
        userMoviePositionalServiceFacade.addMovieToFolder(folderMoviePositionalRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/movies/{movieId}")
    public ResponseEntity<Void> deleteMovieFromFolder(@PathVariable Long id, @PathVariable Long movieId) {

        FolderMoviePositionalResponseDto toDelete = userMoviePositionalServiceFacade.getFolderMoviePositionalByFolderAndMovie(id, movieId);
        userMoviePositionalServiceFacade.deleteFolderMoviePositionalById(toDelete.getId());

        // Уменьшение позиции на 1 каждого фильма, находящегося после удаленного по номеру позиции
        List<FolderMoviePositionalRequestDto> allRecords = userMoviePositionalServiceFacade
                .getAll()
                .stream()
                .filter(x -> x.getPositional() > toDelete.getPositional())
                .map(x -> {
                    FolderMoviePositionalRequestDto r = new FolderMoviePositionalRequestDto();
                    r.setPositional(x.getPositional() - 1);
                    r.setMovie(x.getMovie());
                    r.setFolder(x.getFolder());
                    return r;
                }).toList();
        // Обновление данных в БД
        allRecords.forEach(userMoviePositionalServiceFacade::updateMovie);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/movies/{movieId}?position={p}")
    public ResponseEntity<Void> changeMoviePositionInFolder(@PathVariable Long id,
                                                            @PathVariable Long movieId,
                                                            @PathVariable Integer p) {
        FolderMoviePositionalResponseDto folderMoviePositionalResponseDto =
                userMoviePositionalServiceFacade.getFolderMoviePositionalByFolderAndMovie(id, movieId);

        // Преобразование FolderMoviePositionalResponseDto -> FolderMoviePositionalRequestDto
        FolderMoviePositionalRequestDto folderMoviePositionalRequestDto = new FolderMoviePositionalRequestDto();
        folderMoviePositionalRequestDto.setMovie(folderMoviePositionalResponseDto.getMovie());
        folderMoviePositionalRequestDto.setFolder(folderMoviePositionalResponseDto.getFolder());
        folderMoviePositionalRequestDto.setPositional(p);

        userMoviePositionalServiceFacade.updateMovie(folderMoviePositionalRequestDto);

        return ResponseEntity.ok().build();
    }

}
