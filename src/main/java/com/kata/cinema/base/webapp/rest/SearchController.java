package com.kata.cinema.base.webapp.rest;

import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import com.kata.cinema.base.service.dto.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/search")
    public SearchResponseDto search(@RequestParam String filterPattern) {
        return searchService.search(filterPattern);
    }
}