package com.kata.cinema.base.converter.review;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ConfigMapper.class)
public interface ReviewTitleMapper extends DtoMapper<ReviewTitleResponseDto, Review> {

    @Override
    @Mapping(target = "movieId", expression = "java(review.getMovie().getId())")
    @Mapping(target = "name", expression = "java(review.getMovie().getName())")
    @Mapping(target = "originalName", expression = "java(review.getMovie().getOriginalName())")
    ReviewTitleResponseDto toDto(Review review);
}
