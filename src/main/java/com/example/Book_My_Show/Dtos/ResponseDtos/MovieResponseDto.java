package com.example.Book_My_Show.Dtos.ResponseDtos;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {
    private String movieName;
    private double duration;
    private double rating;
    private Genre genre;
    private Language language;
    private Date releaseDate;
}
