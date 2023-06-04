package com.kata.cinema.base.models.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPersonDto {

    private Long id;
    private String photoUrl;
    private String fullName;
    private String originalFullName;
    private LocalDate birthday;


}