package com.kata.cinema.base.rest.user.UserFolderPersonPositionalRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.webapp.facade.user.UserFolderPersonPositionalServiceFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        Long personId = 105L;

        // When
        mockMvc.perform(post("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что персона была добавлена в фолдер
        FolderPersonPositional folderPersonPositional = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional);
        assertEquals(6, folderPersonPositional.getPositional());

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции персоны в фолдер в сторону увеличения
     */
    @Test
    void givenFolderIdAndPersonIdAndPosition_whenIncreasePersonPositionInFolder_thenUpdateOtherPersonsPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 100L;
        Integer position = 3;


        // When
        // Позиция персоны изменяется с 1 на 3
        mockMvc.perform(put("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .param("position", String.valueOf(position))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция персоны была обновлена в фолдер
        FolderPersonPositional folderPersonPositional1 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional1);
        assertEquals(position, folderPersonPositional1.getPositional());

        // Проверка, что позиции остальных персон были корректно обновлены
        FolderPersonPositional folderPersonPositional2 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 101L);
        FolderPersonPositional folderPersonPositional3 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 102L);
        FolderPersonPositional folderPersonPositional4 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 103L);
        FolderPersonPositional folderPersonPositional5 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 104L);
        assertEquals(position - 2, folderPersonPositional2.getPositional());
        assertEquals(position - 1, folderPersonPositional3.getPositional());
        assertEquals(position + 1, folderPersonPositional4.getPositional());
        assertEquals(position + 2, folderPersonPositional5.getPositional());

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции персоны в фолдер в сторону уменьшения
     */
    @Test
    void givenFolderIdAndPersonIdAndPosition_whenDecreasePersonPositionInFolder_thenUpdateOtherPersonsPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long personId = 103L;
        Integer position = 2;


        // When
        // Позиция персоны изменяется с 4 на 2
        mockMvc.perform(put("/api/user/folders/{id}/persons/{personId}", id, personId)
                        .param("position", String.valueOf(position))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция персоны была обновлена в фолдер
        FolderPersonPositional folderPersonPositional4 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNotNull(folderPersonPositional4);
        assertEquals(position, folderPersonPositional4.getPositional());

        // Проверка, что позиции остальных персон были корректно обновлены
        FolderPersonPositional folderPersonPositional1 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 100L);
        FolderPersonPositional folderPersonPositional2 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 101L);
        FolderPersonPositional folderPersonPositional3 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 102L);
        FolderPersonPositional folderPersonPositional5 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 104L);
        assertEquals(position - 1, folderPersonPositional1.getPositional()); // position = 1 (не изменилась)
        assertEquals(position + 1, folderPersonPositional2.getPositional()); // position = 3 (+1)
        assertEquals(position + 2, folderPersonPositional3.getPositional()); // position = 4 (+1)
        assertEquals(position + 3, folderPersonPositional5.getPositional()); // position = 5 (не изменилась)

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
        FolderPersonPositional folderPersonPositional1 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(id, personId);
        assertNull(folderPersonPositional1);

        // Проверка, что позиции следующих персон были обновлены
        FolderPersonPositional folderPersonPositional2 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 101L);
        FolderPersonPositional folderPersonPositional3 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 102L);
        FolderPersonPositional folderPersonPositional4 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 103L);
        FolderPersonPositional folderPersonPositional5 = userFolderPersonPositionalServiceFacade.getByFolderIdAndPersonId(100L, 104L);
        assertEquals(deletedPersonPosition, folderPersonPositional2.getPositional()); // position = 1 (-1)
        assertEquals(deletedPersonPosition + 1, folderPersonPositional3.getPositional()); // position = 2 (-1)
        assertEquals(deletedPersonPosition + 2, folderPersonPositional4.getPositional()); // position = 3 (-1)
        assertEquals(deletedPersonPosition + 3, folderPersonPositional5.getPositional()); // position = 4 (-1)

    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, персона с таким id не существует
     */
    @Test
    void givenFolderIdAndPersonIdAndPosition_whenChangePersonPositionInFolder_thenFailedTest() {
        // Given
        Long id = 100L;
        Long personId = 999L;
        Integer position = 2;

        // When
        try {
            mockMvc.perform(put("/api/user/folders/{id}/persons/{personId}", id, personId)
                            .param("position", String.valueOf(position))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            // Then
            assertEquals("Персона с таким id = 999 не существует", e.getCause().getMessage());
        }

    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, фолдер с таким id не существует
     */
    @Test
    void givenFolderIdAndPersonId_whenAddPersonToFolder_thenFailedTest() {
        // Given
        Long id = 999L;
        Long personId = 100L;

        // When
        try {
            mockMvc.perform(post("/api/user/folders/{id}/persons/{movieId}", id, personId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            // Then
            assertEquals("Папка с таким id = 999 не существует", e.getCause().getMessage());
        }
    }

}
