package com.example.Book_My_Show.Dtos.RequestDtos;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
@Data
public class MovieEntryDto {

    private String movieName;
    private int duration;
    private double rating;
    private Genre genre;
    private Language language;
    private Date releaseDate;

}
