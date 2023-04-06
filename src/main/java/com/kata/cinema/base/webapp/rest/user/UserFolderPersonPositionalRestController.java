package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.dto.request.FolderPersonPositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
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
public class UserFolderPersonPositionalRestController {

    private final UserFolderPersonPositionalServiceFacade userPersonPositionalServiceFacade;

    @PostMapping("/{id}/persons/{personId}")
    public ResponseEntity<Void> addPersonToFolder(@PathVariable Long id,
                                                 @PathVariable Long personId,
                                                 @Valid @RequestBody FolderPersonPositionalRequestDto folderPersonPositionalRequestDto) {

        // Сортировка полученных записей из БД (FolderPersonPositional) по возрастанию позиций
        List<FolderPersonPositionalResponseDto> allRecords = userPersonPositionalServiceFacade
                .getAll()
                .stream()
                .sorted(Comparator.comparing(FolderPersonPositionalResponseDto::getPositional))
                .toList();

        // Получение позиции последней персоны для заполнения поля positional добавленной персоны
        Integer newPersonPosition = allRecords.get(allRecords.size() - 1).getPositional() + 1;
        folderPersonPositionalRequestDto.setPositional(newPersonPosition);
        userPersonPositionalServiceFacade.addPersonToFolder(folderPersonPositionalRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/persons/{personId}")
    public ResponseEntity<Void> deletePersonFromFolder(@PathVariable Long id, @PathVariable Long personId) {

        FolderPersonPositionalResponseDto toDelete = userPersonPositionalServiceFacade.getFolderPersonPositionalByFolderAndPerson(id, personId);
        userPersonPositionalServiceFacade.deleteFolderPersonPositionalById(toDelete.getId());

        // Уменьшение позиции на 1 каждого фильма, находящегося после удаленного по номеру позиции
        List<FolderPersonPositionalRequestDto> allRecords = userPersonPositionalServiceFacade
                .getAll()
                .stream()
                .filter(x -> x.getPositional() > toDelete.getPositional())
                .map(x -> {
                    FolderPersonPositionalRequestDto r = new FolderPersonPositionalRequestDto();
                    r.setPositional(x.getPositional() - 1);
                    r.setPerson(x.getPerson());
                    r.setFolder(x.getFolder());
                    return r;
                }).toList();
        // Обновление данных в БД
        allRecords.forEach(userPersonPositionalServiceFacade::updatePerson);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/persons/{personId}?position={p}")
    public ResponseEntity<Void> changePersonPositionInFolder(@PathVariable Long id,
                                                            @PathVariable Long personId,
                                                            @PathVariable Integer p) {
        FolderPersonPositionalResponseDto folderPersonPositionalResponseDto =
                userPersonPositionalServiceFacade.getFolderPersonPositionalByFolderAndPerson(id, personId);

        // Преобразование FolderPersonPositionalResponseDto -> FolderPersonPositionalRequestDto
        FolderPersonPositionalRequestDto folderPersonPositionalRequestDto = new FolderPersonPositionalRequestDto();
        folderPersonPositionalRequestDto.setPerson(folderPersonPositionalResponseDto.getPerson());
        folderPersonPositionalRequestDto.setFolder(folderPersonPositionalResponseDto.getFolder());
        folderPersonPositionalRequestDto.setPositional(p);

        userPersonPositionalServiceFacade.updatePerson(folderPersonPositionalRequestDto);

        return ResponseEntity.ok().build();
    }
}
