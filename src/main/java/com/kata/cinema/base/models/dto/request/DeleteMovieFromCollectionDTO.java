package com.kata.cinema.base.models.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMovieFromCollectionDTO {

    private List<Long> ids;

}
