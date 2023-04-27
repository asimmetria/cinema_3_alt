package com.kata.cinema.base.rest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.kata.cinema.base.webapp.auth.AuthenticationRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class IntegrationTestingAccessTokenUtil {


    public static String obtainNewAccessToken(final String username, final String password, MockMvc mockMvc) throws Exception {
        AuthenticationRequest user = new AuthenticationRequest();
        user.setEmail(username);
        user.setPassword(password);

        ObjectMapper objectMapper = new ObjectMapper();
        return JsonPath.read(mockMvc.perform(
                        post("/api/auth/authenticate")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), "$.token");
    }

}
