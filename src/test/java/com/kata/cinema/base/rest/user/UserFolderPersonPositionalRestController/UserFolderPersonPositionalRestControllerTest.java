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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

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
        Long personId = 100L;

        // When
        mockMvc.perform(post("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .contentType(MediaType.APPLICATION_JSON))
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
    void givenFolderIdAndPersonIdAndPosition_whenChangePersonPositionInFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 100L;
        Integer position = 1;
        Integer newPosition = 2;

        // When
        mockMvc.perform(put("/api/user/folders/{id}/persons/{personId}?position={position}", id, personId, position)
                        .param("positional", String.valueOf(newPosition))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция персоны была обновлена в фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional);
        assertEquals(newPosition, folderPersonPositional.getPositional());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление персоны из фолдер
     */
    @Test
    void givenFolderIdAndPersonId_whenDeletePersonFromFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 100L;

        // When
        mockMvc.perform(delete("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        // Проверка, что персона была удалена из фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNull(folderPersonPositional);
    }

}
