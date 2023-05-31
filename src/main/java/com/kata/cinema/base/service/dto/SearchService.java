package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.SearchResponseDto;

public interface SearchService {
    SearchResponseDto search(String filterPattern);
}
