package com.kata.cinema.base.converter.review;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ConfigMapper.class)
public interface ReviewMapper extends DtoMapper<ReviewResponseDto, Review>, EntityMapper<ReviewRequestDto, Review> {
    @Override
    @Mapping(target = "fullName", expression = "java(review.getUser().getName() + \" \" + review.getUser().getLastName())")
    ReviewResponseDto toDto(Review review);
}
