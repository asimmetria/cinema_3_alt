package com.kata.cinema.base.rest.user.UserFolderPersonPositionalRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderPersonPositionalRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderPersonPositionalRestController/after.sql")
public class UserFolderPersonPositionalRestControllerTest extends SpringContextTest {


    @Autowired
    private UserFolderPersonPositionalServiceFacade userFolderPersonPositionalServiceFacade;


    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление персоны в фолдер
     */
    @Test
    void givenFolderIdAndPersonId_whenAddPersonToFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 103L;

        // When
        mockMvc.perform(post("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что персона была добавлена в фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional);

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции персоны в фолдер
     */
    @Test
    void givenFolderIdAndPersonIdAndPosition_whenChangePersonPositionInFolder_thenUpdateNextPersonsPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 100L;
        Integer position = 2;


        // When
        mockMvc.perform(put("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .param("position", String.valueOf(position))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция персоны была обновлена в фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional);
        assertEquals(position, folderPersonPositional.getPositional());

        // Проверка, что позиция следующих персон была обновлена
        FolderPersonPositional folderPersonPositional1 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 101L);
        FolderPersonPositional folderPersonPositional2 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 102L);
        assertEquals(position + 1, folderPersonPositional1.getPositional());
        assertEquals(position + 2, folderPersonPositional2.getPositional());

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление персоны из фолдер
     */
    @Test
    void givenFolderIdAndPersonId_whenDeletePersonFromFolder_thenUpdateNextPersonsPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 100L;
        Integer deletedPersonPosition = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId).getPositional();

        // When
        mockMvc.perform(delete("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что персона была удалена из фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNull(folderPersonPositional);

        // Проверка, что позиция следующей персоны была обновлена
        FolderPersonPositional folderPersonPositional1 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 101L);
        FolderPersonPositional folderPersonPositional2 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 102L);
        assertEquals(deletedPersonPosition, folderPersonPositional1.getPositional());
        assertEquals(deletedPersonPosition + 1, folderPersonPositional2.getPositional());

    }

}
