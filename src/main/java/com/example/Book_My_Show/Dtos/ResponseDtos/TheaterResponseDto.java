package com.example.Book_My_Show.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponseDto {
    private String theaterName;
    private String movieName;
    private Date date;
    private String location;
}
