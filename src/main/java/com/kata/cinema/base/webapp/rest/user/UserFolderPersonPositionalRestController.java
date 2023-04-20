package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/folders")
@RequiredArgsConstructor
public class UserFolderPersonPositionalRestController {

    private final UserFolderPersonPositionalServiceFacade userPersonPositionalServiceFacade;

    @PostMapping("/{id}/persons/{personId}")
    public ResponseEntity<Void> addPersonToFolder(@PathVariable Long id,
                                                 @PathVariable Long personId) {
        userPersonPositionalServiceFacade.createFolderPersonPositional(id, personId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/persons/{personId}")
    public ResponseEntity<Void> deletePersonFromFolder(@PathVariable Long id, @PathVariable Long personId) {
        FolderPersonPositional folderPersonPositional =
                userPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        userPersonPositionalServiceFacade.deleteFolderPersonPositionalById(folderPersonPositional.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/persons/{personId}")
    public ResponseEntity<Void> changePersonPositionInFolder(@PathVariable Long id,
                                                             @PathVariable Long personId,
                                                             @RequestParam("position") Integer position) {
        userPersonPositionalServiceFacade.updateFolderPersonPositional(id, personId, position);
        return ResponseEntity.ok().build();
    }
}
