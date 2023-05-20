package com.kata.cinema.base.my;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.dto.MovieSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieSearchControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MovieSearchService movieSearchService;

    @Test
    public void searchMovies_ReturnsSearchResults() {
        // Mock the searchMovies method of the movieSearchService
        Page<SearchMovieResponseDto> searchResults = createSampleSearchResults();
        Mockito.when(movieSearchService.searchMovies(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(),
                Mockito.any(LocalDate.class), Mockito.any(LocalDate.class), Mockito.anyList(), Mockito.any(RARS.class),
                Mockito.any(MPAA.class), Mockito.any(MovieSortType.class))).thenReturn(searchResults);

        // Perform the HTTP request
        ResponseEntity<Page<SearchMovieResponseDto>> response = restTemplate.exchange(
                "/api/search/movies/page/1?name=Avengers&startDate=2022-01-01&endDate=2022-12-31&genres=Action&sortType=DATE_RELEASE_ASC",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<SearchMovieResponseDto> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(searchResults.getTotalElements(), responseBody.getTotalElements());
        // ... assert other properties of the response

        // Verify that the movieSearchService method was called with the correct parameters
        Mockito.verify(movieSearchService).searchMovies(1, 10, "Avengers", LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31), Collections.singletonList("Action"), null, null,
                MovieSortType.DATE_RELEASE_ASC);
    }

    private Page<SearchMovieResponseDto> createSampleSearchResults() {
        // Create a sample list of SearchMovieResponseDto
        List<SearchMovieResponseDto> results = new ArrayList<>();
        // ... populate the list with sample data
        // You can use Mocking or create actual instances of SearchMovieResponseDto

        // Create a Page object from the list
        return new PageImpl<>(results);
    }
}

