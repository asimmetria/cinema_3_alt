package com.kata.cinema.base.models.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchMovieDto {

   private Long id;
   private String name;
   private String originalName;
   private String previewUrl;
   private LocalDate date;
   private Integer avgScore;

}
