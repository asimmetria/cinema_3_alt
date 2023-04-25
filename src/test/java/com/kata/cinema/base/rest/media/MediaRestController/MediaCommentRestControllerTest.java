package com.kata.cinema.base.rest.media.MediaRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.webapp.facade.unauthorized.MediaCommentServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaCommentRestControllerTest {

    private static final Long MEDIA_COMMENT_ID = 1L;
    private static final Integer MEDIA_COMMENT_LEVEL = 1;
    private static final String MEDIA_COMMENT_MESSAGE = "TestMessage";

    private static final UserNameResponseDto user = null;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaCommentServiceFacade mediaCommentServiceFacade;


    @Test
    public void testGetComment() throws Exception {
        UserCommentResponseDto userCommentResponseDto = new UserCommentResponseDto();
        userCommentResponseDto.setId(MEDIA_COMMENT_ID);
        userCommentResponseDto.setLevel(MEDIA_COMMENT_LEVEL);
        userCommentResponseDto.setMessage(MEDIA_COMMENT_MESSAGE);
        userCommentResponseDto.setParentId(MEDIA_COMMENT_ID);
        userCommentResponseDto.setUser(user);

        Page<UserCommentResponseDto> expectedPage = new PageImpl<>(Collections.singletonList(userCommentResponseDto));

        when(mediaCommentServiceFacade.getComments(anyLong())).thenReturn(expectedPage);

        MvcResult mvcResult = mockMvc.perform(get("/api/media/{id}/comments", MEDIA_COMMENT_ID))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(asJsonString(expectedPage), actualJson, true);
    }

    @Test
    public void testSendComment() throws Exception {
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setParentId(MEDIA_COMMENT_ID);
        commentRequestDto.setLevel(MEDIA_COMMENT_LEVEL);
        commentRequestDto.setMessage(MEDIA_COMMENT_MESSAGE);

        mockMvc.perform(post("/api/media/{id}/comments", MEDIA_COMMENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentRequestDto)))
                .andExpect(status().isOk());

        verify(mediaCommentServiceFacade, times(1)).sendComment(MEDIA_COMMENT_ID, commentRequestDto);
    }

    @Test
    public void testEditComment() throws Exception {
        mockMvc.perform(patch("/api/media/{id}/comments/{commentId}", MEDIA_COMMENT_ID, MEDIA_COMMENT_ID)
                        .param("message", MEDIA_COMMENT_MESSAGE))
                .andExpect(status().isOk());

        verify(mediaCommentServiceFacade, times(1)).editComment(MEDIA_COMMENT_MESSAGE, MEDIA_COMMENT_ID, MEDIA_COMMENT_ID);
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(delete("/api/media/{id}/comments/{commentId}", MEDIA_COMMENT_ID, MEDIA_COMMENT_ID))
                .andExpect(status().isNoContent());

        verify(mediaCommentServiceFacade, times(1)).deleteComment(MEDIA_COMMENT_ID, MEDIA_COMMENT_ID);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}