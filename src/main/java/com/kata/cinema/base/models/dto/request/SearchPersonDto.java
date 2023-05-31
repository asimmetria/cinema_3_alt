package com.kata.cinema.base.models.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPersonDto {

    Long id;
    String photoUrl;
    String fullName;
    String originalFullName;
    LocalDate birthday;


}